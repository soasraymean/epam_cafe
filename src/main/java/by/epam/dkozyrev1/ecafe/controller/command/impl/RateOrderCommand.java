package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class RateOrderCommand extends Command {

    public RateOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final String key = getRequest().getParameter("key");
            if (Objects.nonNull(key) && !key.isEmpty() && !key.isBlank() && key.matches("\\d++")) {
                ((Client)((HttpServletRequest)getRequest()).getSession().getAttribute("actor")).getOrder(Integer.parseInt(key))
                        .ifPresent( order -> {
                            rateOrder(order);
                            try {
                                EntityServiceFactory.getInstance().getOrderService().update(order);
                            } catch (ServiceException ex) {
                                StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Order hasn't been rated cause of %s", ex));
                            }
                });
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                        (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                                "/order_info?key=" + key : "/client_orders"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

    private void rateOrder(Order order) {
        final String[] rating = getRequest().getParameterValues("rating");
        order.setClientMark(
                Arrays.toString(rating).contains("5") ? 5 :
                        Arrays.toString(rating).contains("4") ? 4 :
                                Arrays.toString(rating).contains("3") ? 3 :
                                        Arrays.toString(rating).contains("2") ? 2 : 1);
        order.setClientComment(
                Objects.nonNull(getRequest().getParameter("message")) ?
                getRequest().getParameter("message") :
                "");
    }

}
