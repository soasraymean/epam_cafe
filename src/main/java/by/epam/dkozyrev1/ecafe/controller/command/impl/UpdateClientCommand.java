package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class UpdateClientCommand extends AdminCommand {

    public UpdateClientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String updateKey = getRequest().getParameter("ukey");
            if(Objects.nonNull(updateKey) && !updateKey.isEmpty() && !updateKey.isBlank() && updateKey.matches("\\d++")) {
                EntityServiceFactory.getInstance().getClientService().find(Integer.parseInt(updateKey)).ifPresent(this::update);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    private void update(Client client) {
        try {
            if (setLoyalty(client) && setBonuses(client)) {
                setBanned(client);
                EntityServiceFactory.getInstance().getClientService().update(client);
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                        (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                                "/admin_client_info?key=" :
                                "/admin_clients?open=") + getRequest().getParameter("ukey"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
//            StaticDataHandler.getInstance().getLOGGER().error(String.format("Client %s hasn't been updated cause of %s", client, ex));
        }
    }

    private void setBanned(Client client) {
        client.setBanned(Arrays.toString(getRequest().getParameterValues("params")).contains("isBanned"));
    }

    private boolean setBonuses(Client client) {
        final String clientBonuses = getRequest().getParameter("clientBonuses");
        if (Objects.nonNull(clientBonuses) && !clientBonuses.isEmpty() && !clientBonuses.isBlank()) {
            client.setBonuses(Integer.parseInt(clientBonuses.replace(" ", "")));
            return true;
        } else {
            return false;
        }
    }

    private boolean setLoyalty(Client client) {
        final String clientLoyalty = getRequest().getParameter("clientLoyalty");
        if (Objects.nonNull(clientLoyalty) && !clientLoyalty.isEmpty() && !clientLoyalty.isBlank()) {
            client.setLoyaltyPoints(Integer.parseInt(clientLoyalty.replace(" ", "")));
            return true;
        } else {
            return false;
        }
    }

}
