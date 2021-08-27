package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Admin;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ChangeAdminProfileCommand extends AdminCommand {

    public ChangeAdminProfileCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final Admin actor = (Admin)((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            final String username = getRequest().getParameter("username");
            if (Objects.nonNull(username) && !username.isEmpty() && !username.isBlank()) {
                actor.getUser().setUsername(username);
            }
            final String password = getRequest().getParameter("password");
            if (Objects.nonNull(password) && !password.isEmpty() && !password.isBlank()) {
                actor.getUser().setPassword(password);
            }
            EntityServiceFactory.getInstance().getUserService().update(actor.getUser());
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
