package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Client;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveMealFromOrderCommand extends Command {

    public RemoveMealFromOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        executePost();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            ((Client)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).getCurrentOrder().removeMeal(Integer.parseInt(getRequest().getParameter("key")));
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath()+"/client_order");
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
