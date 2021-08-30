package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
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

public class SignUpCommand extends Command {

    public SignUpCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            ((HttpServletRequest) getRequest()).getSession().setAttribute("actor",
                    AuthorizationService.getInstance().authorize(
                    getRequest().getParameter("username"),
                    getRequest().getParameter("password"),
                    getRequest().getParameter("email"),
                    getRequest().getParameter("phone"),
                    getRequest().getParameter("name")));
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/");
        } catch (ServiceException | IOException ex) {
            StaticDataHandler.getInstance().getLOGGER().error(ex);
            StaticDataHandler.getInstance().getLOGGER().error(Arrays.toString(ex.getStackTrace()));
            getRequest().setAttribute("error",
                    Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("locale")) ?
                    switch (((HttpServletRequest) getRequest()).getSession().getAttribute("locale").toString()){
                        case "by" -> "Niaslushnyia dadzienyia abo karystalnik ujo zarehistravany";
                        case "ru" -> "Неверные данные или данный пользователь уже зарегестрирован";
                        default -> "Invalid data or account already been registered";
                    } : "Invalid data or account already been registered");
            executeGet();
        }
    }

}
