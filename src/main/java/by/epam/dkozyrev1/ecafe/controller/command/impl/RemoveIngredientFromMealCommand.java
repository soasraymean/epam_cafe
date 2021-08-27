package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RemoveIngredientFromMealCommand extends AdminCommand {

    public RemoveIngredientFromMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String updateKey = getRequest().getParameter("ukey");
            if (Objects.nonNull(updateKey) && !updateKey.isBlank() && !updateKey.isEmpty() && updateKey.matches("\\d++")) {
                EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(updateKey)).ifPresent(meal -> {
                    try {
                        meal.removeIngredientId(Integer.parseInt(getRequest().getParameter("rkey")));
                        EntityServiceFactory.getInstance().getMealService().update(meal);
                    } catch (ServiceException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Meal %s hasn't been updated cause of %s ", meal, ex));
                    }
                });
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_meal_info?key=" + updateKey);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
