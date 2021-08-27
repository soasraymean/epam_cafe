package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class PlaceOrderCommand extends Command {

    public PlaceOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            if (LocalTime.parse(getRequest().getParameter("orderTime"), timeFormat).isBefore(LocalTime.now().plusMinutes(30))) {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/client_order?status=timeError");
                return;
            }
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            final Client actor =
                    (Client) session.getAttribute("actor");
            actor.getCurrentOrder().setOrderDate(Date.from(LocalTime.parse(getRequest().getParameter("orderTime"), timeFormat).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
            if (Objects.nonNull(getRequest().getParameter("offlinePayment"))) {
                EntityServiceFactory.getInstance().getOrderService().save(actor.getCurrentOrder()).ifPresent(savedOrder -> {
                    actor.addOrder(savedOrder);
                    actor.setCurrentOrder(new Order(actor));
                });
            } else {
                if(Arrays.toString(getRequest().getParameterValues("params")).contains("payWithBonuses")){
                    session.setAttribute("moneyToBePaid",
                            actor.getCurrentOrder().getTotalPrice()/2);
                    session.setAttribute("bonusesToBePaid",
                            actor.getCurrentOrder().getTotalPrice()/2);
                } else {
                    session.setAttribute("moneyToBePaid",
                            actor.getCurrentOrder().getTotalPrice());
                }
                getRequest().getRequestDispatcher("/WEB-INF/jsp/payment.jsp").forward(getRequest(), getResponse());
                return;
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile?status=success");
        } catch (ServiceException | IOException | ServletException ex) {
            throw new ControllerException(ex);
        }
    }

}
