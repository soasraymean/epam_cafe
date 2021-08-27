package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SaveMealCommand extends AdminCommand {

    public SaveMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final Meal meal = new Meal();
            final String mealName = getRequest().getParameter("mealName");
            if (Objects.nonNull(mealName) && !mealName.isEmpty() && !mealName.isBlank()) {
                meal.setName(mealName);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String mealPictureUrl = getRequest().getParameter("mealPictureUrl");
            if (Objects.nonNull(mealPictureUrl) && !mealPictureUrl.isEmpty() && !mealPictureUrl.isBlank()) {
                meal.setPictureUrl(mealPictureUrl);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String mealPrice = getRequest().getParameter("mealPrice");
            if (Objects.nonNull(mealPrice) && !mealPrice.isEmpty() && !mealPrice.isBlank()) {
                meal.setPrice(Integer.parseInt(mealPrice));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String mealCategoryName = getRequest().getParameter("mealCategoryName");
            if(Objects.nonNull(mealCategoryName)) {
                EntityServiceFactory.getInstance().getMealCategoryService().find(mealCategoryName).ifPresent(meal::setCategory);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            EntityServiceFactory.getInstance().getMealService().save(meal);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_menu?key=all");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
