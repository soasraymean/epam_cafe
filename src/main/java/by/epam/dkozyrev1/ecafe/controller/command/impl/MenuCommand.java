package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class MenuCommand extends Command {

    public MenuCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if(Objects.nonNull(key) && !key.isBlank() && !key.isEmpty() && key.matches("\\d++")) {
                final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
                session.removeAttribute("meals");
                session.setAttribute("meals",
                        EntityServiceFactory.getInstance().getMealService().getList(Integer.parseInt(key)));
            }
            getRequest().setAttribute("categories",
                    EntityServiceFactory.getInstance().getMealCategoryService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        throw new UnsupportedOperationException();
    }

}
