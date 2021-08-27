package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class OrderInfoCommand extends Command {

    public OrderInfoCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if(Objects.nonNull(key) && !key.isBlank() && !key.isEmpty() && key.matches("\\d++")) {
                EntityServiceFactory.getInstance().getOrderService().find(Integer.parseInt(key)).ifPresent(this::resetOrderAttribute);
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/orderinfo.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

    private void resetOrderAttribute(Order newAttribute){
        ((HttpServletRequest) getRequest()).getSession().removeAttribute("order");
        ((HttpServletRequest) getRequest()).getSession().setAttribute("order", newAttribute);
    }

}
