package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Actor;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class ProfileCommand extends Command {

    public ProfileCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final Actor actor = (Actor) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            if (Objects.nonNull(actor) && !actor.isPromoted()) {
                final Order currentOrder = ((Client)actor).getCurrentOrder();
                ((HttpServletRequest)getRequest()).getSession().removeAttribute("actor");
                EntityServiceFactory.getInstance().getClientService().find(actor.getId()).ifPresent(a -> {
                    if(a.isBanned()){
                        try {
                            getRequest().getRequestDispatcher("/WEB-INF/jsp/bannedinfopage.jsp").forward(getRequest(), getResponse());
                            return;
                        } catch (ServletException | IOException ex) {
                            StaticDataHandler.getInstance().getLOGGER().error(ex);
                        }
                    }
                    a.setCurrentOrder(currentOrder);
                    ((HttpServletRequest) getRequest()).getSession().setAttribute("actor", a);
                });
            }
            if(Objects.nonNull(actor)){
                getRequest().getRequestDispatcher(actor.isPromoted() ?
                        "/WEB-INF/jsp/admin/adminprofile.jsp" :
                        "/WEB-INF/jsp/profile.jsp").forward(getRequest(), getResponse());
            } else {
                getRequest().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(getRequest(), getResponse());
            }

        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        throw new UnsupportedOperationException();
    }

}
