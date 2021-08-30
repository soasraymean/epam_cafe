package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Actor;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.service.authorization.impl.AuthorizationService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class SignInCommand extends Command {

    public SignInCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Actor actor = AuthorizationService.getInstance().authorize(
                    getRequest().getParameter("username"),
                    getRequest().getParameter("password"));
            if (!actor.isPromoted() && ((Client) actor).isBanned()) {
                getRequest().getRequestDispatcher("/WEB-INF/jsp/bannedinfopage.jsp").forward(getRequest(), getResponse());
                return;
            }
            ((HttpServletRequest) getRequest()).getSession().setAttribute("actor", actor);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/");
        } catch (ServiceException | IOException | ServletException ex) {
            StaticDataHandler.getInstance().getLOGGER().error(ex);
            StaticDataHandler.getInstance().getLOGGER().error(Arrays.toString(ex.getStackTrace()));
            getRequest().setAttribute("error",
                    Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("locale")) ?
                    switch (((HttpServletRequest) getRequest()).getSession().getAttribute("locale").toString()){
                        case "by" -> "Niaslushny lahin ci parol";
                        case "ru" -> "Неверный логин или пароль";
                        default -> "Invalid login or password";
                    } : "Invalid login or password");
            executeGet();
        }
    }

}
