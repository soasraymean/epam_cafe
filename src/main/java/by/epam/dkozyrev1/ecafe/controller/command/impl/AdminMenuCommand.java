package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
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

public class AdminMenuCommand extends AdminCommand {

    public AdminMenuCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            if(Objects.nonNull(key) && !key.isBlank() && !key.isEmpty() && (key.matches("\\d++") || key.equals("all"))) {
                        session.removeAttribute("meals");
                        session.setAttribute("meals", key.equals("all") ?
                        EntityServiceFactory.getInstance().getMealService().getList() :
                        EntityServiceFactory.getInstance().getMealService().getList(Integer.parseInt(key)));
            }
            session.setAttribute("categories",
                    EntityServiceFactory.getInstance().getMealCategoryService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminmenu.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
