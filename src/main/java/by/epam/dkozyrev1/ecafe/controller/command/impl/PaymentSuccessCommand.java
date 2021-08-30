package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class PaymentSuccessCommand extends Command {

    public PaymentSuccessCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        executePost();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            final Client actor = (Client) session.getAttribute("actor");
            final Order order = actor.getCurrentOrder();
            order.setPaid(true);
            EntityServiceFactory.getInstance().getOrderService().save(order).ifPresent(savedOrder -> {
                actor.addOrder(savedOrder);
                actor.setCurrentOrder(new Order(actor));
                if(Objects.nonNull(session.getAttribute("bonusesToBePaid"))){
                    actor.setBonuses(actor.getBonuses() - Integer.parseInt(session.getAttribute("bonusesToBePaid").toString()));
                }
                try {
                    EntityServiceFactory.getInstance().getClientService().update(actor);
                } catch (ServiceException ex) {
                    StaticDataHandler.getInstance().getLOGGER().error(String.format("Client hasn't been updated cause of %s", ex));
                }
            });
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile?status=success");
        } catch (ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
