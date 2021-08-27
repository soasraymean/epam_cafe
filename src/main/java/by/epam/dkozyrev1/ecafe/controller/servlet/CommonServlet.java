package by.epam.dkozyrev1.ecafe.controller.servlet;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.command.Command;
import by.epam.dkozyrev1.ecafe.controller.command.WebCommandType;
import by.epam.dkozyrev1.ecafe.controller.filter.CommonUrlFilter;
import by.epam.dkozyrev1.ecafe.controller.localisation.LocalisationService;
import by.epam.dkozyrev1.ecafe.controller.security.RequestScriptingFilter;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class CommonServlet extends HttpServlet {

    @Override
    @ExceptionableBeingLogged
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (doPreparation(req, resp)) {
                final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
                webCommand.executeGet();
            }
        } catch (Exception ex){
            ex.printStackTrace();
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                StaticDataHandler.INSTANCE.getLOGGER().error(e);
            }
        }
    }

    @Override
    @ExceptionableBeingLogged
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            if (doPreparation(req, resp)) {
                final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
                webCommand.executePost();
            }
        } catch (Exception ex){
            ex.printStackTrace();
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                StaticDataHandler.INSTANCE.getLOGGER().error(e);
            }
        }
    }

    private boolean doPreparation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LocalisationService.setLocale(req, resp);
        if (RequestScriptingFilter.filter(req)) {
            return true;
        } else {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/attack_answer");
            return false;
        }
    }


}