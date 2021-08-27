package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class AdminOrderInfoCommand extends AdminCommand {

    public AdminOrderInfoCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String key = getRequest().getParameter("key");
            if(Objects.nonNull(key) && !key.isBlank() && !key.isEmpty() && key.matches("\\d++")) {
                ((HttpServletRequest) getRequest()).getSession().setAttribute("order",
                                    EntityServiceFactory.getInstance().getOrderService().find(Integer.parseInt(key)).orElseThrow());
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminorderinfo.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
