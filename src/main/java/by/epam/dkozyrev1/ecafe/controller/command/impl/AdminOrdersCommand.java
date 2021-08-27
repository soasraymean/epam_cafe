package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminOrdersCommand extends AdminCommand {

    public AdminOrdersCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if(Objects.nonNull(key) && !key.isBlank() && !key.isEmpty() && (key.matches("\\d++") || key.equals("all") || key.equals("active"))) {
                ((HttpServletRequest) getRequest()).getSession().removeAttribute("orders");
                ((HttpServletRequest) getRequest()).getSession().setAttribute("orders",
                switch (key) {
                    case "all" ->
                            EntityServiceFactory.getInstance().getOrderService().getList();
                    case "active" ->
                            EntityServiceFactory.getInstance().getOrderService().getList()
                            .stream()
                            .filter(Predicate.not(Order::isTaken))
                            .collect(Collectors.toList());
                    default -> EntityServiceFactory.getInstance().getOrderService().getList(Integer.parseInt(key));
                });
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminorders.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
