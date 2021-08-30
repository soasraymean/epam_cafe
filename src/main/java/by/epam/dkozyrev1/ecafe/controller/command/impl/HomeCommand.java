package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Actor;
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

public class HomeCommand extends Command {

    public HomeCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final Actor actor = (Actor) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            if(Objects.nonNull(actor) && actor.isPromoted()) {
                getRequest().setAttribute("activesNumber",
                        EntityServiceFactory.getInstance().getOrderService().getList()
                                .stream()
                                .filter(Predicate.not(Order::isTaken))
                                .count());
             }
             getRequest().getRequestDispatcher(Objects.nonNull(actor) && actor.isPromoted() ?
                     "/WEB-INF/jsp/admin/ahome.jsp" :
                     "/WEB-INF/jsp/home.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            //throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        throw new UnsupportedOperationException();
    }

}
