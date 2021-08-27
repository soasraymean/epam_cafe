package by.epam.dkozyrev1.ecafe.controller.command.impl;


import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
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

public class AdminMealInfoCommand extends AdminCommand {

    public AdminMealInfoCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if(Objects.nonNull(key) && !key.isBlank() && !key.isEmpty() && key.matches("\\d++")) {
                final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
                session.removeAttribute("meal");
                session.setAttribute("meal",
                        EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(key)).orElseThrow());
                session.setAttribute("categories",
                        EntityServiceFactory.getInstance().getMealCategoryService().getList());
                session.setAttribute("ingredients",
                        EntityServiceFactory.getInstance().getMealIngredientService().getList());
                getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminmealinfo.jsp").forward(getRequest(), getResponse());
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
