package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Ingredient;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UpdateIngredientCommand extends AdminCommand {

    public UpdateIngredientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String updateKey = getRequest().getParameter("ukey");
            if(Objects.nonNull(updateKey) && !updateKey.isBlank() && !updateKey.isEmpty() && updateKey.matches("\\d++")) {
                EntityServiceFactory.getInstance().getMealIngredientService().find(Integer.parseInt(updateKey)).ifPresent(this::update);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }


    private void update(Ingredient ingredient){
        try {
            if(setName(ingredient) && setPictureUrl(ingredient)){
                EntityServiceFactory.getInstance().getMealIngredientService().update(ingredient);
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_ingredients");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (ServiceException | IOException ex) {
            StaticDataHandler.getInstance().getLOGGER().error(String.format("Ingredients %s hasn't been updated cause of %s", ingredient, ex));
        }

    }

    private boolean setPictureUrl(Ingredient ingredient) {
        final String ingredientPictureUrl = getRequest().getParameter("ingredientPictureUrl");
        if (Objects.nonNull(ingredientPictureUrl) && !ingredientPictureUrl.isEmpty() && !ingredientPictureUrl.isBlank()) {
            ingredient.setPictureUrl(ingredientPictureUrl);
            return true;
        } else {
            return false;
        }
    }

    private boolean setName(Ingredient ingredient) {
        final String ingredientName = getRequest().getParameter("ingredientName");
        if (Objects.nonNull(ingredientName) && !ingredientName.isEmpty() && !ingredientName.isBlank()) {
            ingredient.setName(ingredientName);
            return true;
        } else {
            return false;
        }
    }

}
