package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UpdateOrderCommand extends AdminCommand {

    public UpdateOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String updateKey = getRequest().getParameter("ukey");
            if(Objects.nonNull(updateKey) && !updateKey.isBlank() && !updateKey.isEmpty() && updateKey.matches("\\d++")) {
                ((List<Order>)((HttpServletRequest) getRequest()).getSession().getAttribute("orders"))
                        .stream()
                        .filter(o -> o.getId() == Integer.parseInt(updateKey))
                        .findAny().ifPresent(this::update);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

    private void update(Order order){
        try {
            final String[] params = getRequest().getParameterValues("params");
            order.setPaid(Arrays.toString(params).contains("isPaid"));
            order.setReady(Arrays.toString(params).contains("isPrepared"));
            order.setTaken(Arrays.toString(params).contains("isTaken"));
            EntityServiceFactory.getInstance().getOrderService().update(order);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                        "/admin_order_info?key=" + order.getId() :
                        "/admin_orders"));
        } catch (IOException | ServiceException ex) {
            StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Order %s hasn't been updated cause of %s", order, ex));
        }
    }

}
