package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AddMealToOrderCommand extends Command {

    public AddMealToOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if (Objects.nonNull(key) && !key.isEmpty() && !key.isBlank() && key.matches("\\d++")) {
                EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(key)).ifPresent(
                                ((Client)((HttpServletRequest)getRequest()).getSession().getAttribute("actor")).getCurrentOrder()::addMeal);
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/menu?status=success");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
