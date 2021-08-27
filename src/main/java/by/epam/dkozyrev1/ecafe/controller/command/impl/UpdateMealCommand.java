package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UpdateMealCommand extends AdminCommand {

    public UpdateMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String updateKey = getRequest().getParameter("ukey");
            if(Objects.nonNull(updateKey) && !updateKey.isBlank() && !updateKey.isEmpty() && updateKey.matches("\\d++")) {
                ((List<Meal>)((HttpServletRequest) getRequest()).getSession().getAttribute("meals"))
                        .stream()
                        .filter(m -> m.getId() == Integer.parseInt(updateKey))
                        .findAny().ifPresent(this::update);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

    private void update(Meal meal){
        try {
            if (setName(meal) && setPictureUrl(meal) && setPrice(meal) && setCategory(meal)) {
                EntityServiceFactory.getInstance().getMealService().update(meal);
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                        (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                                "/admin_meal_info?key=" :
                                "/admin_menu?key=") + getRequest().getParameter("key"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex){
//            StaticDataHandler.getInstance().getLOGGER().error(String.format("Meal %s hasn't been updated cause of %s", meal, ex));
        }
    }

    private boolean setCategory(Meal meal) throws ServiceException {
        if (Objects.nonNull(getRequest().getParameter("mealCategoryName"))) {
            EntityServiceFactory.getInstance().getMealCategoryService().find(getRequest().getParameter("mealCategoryName")).ifPresent(meal::setCategory);
            return true;
        } else {
            return false;
        }
    }

    private boolean setPrice(Meal meal){
        final String mealPrice = getRequest().getParameter("mealPrice");
        if (Objects.nonNull(mealPrice) && !mealPrice.isEmpty() && !mealPrice.isBlank()) {
            meal.setPrice(Integer.parseInt(mealPrice));
            return true;
        } else {
            return false;
        }
    }

    private boolean setPictureUrl(Meal meal){
        final String mealPictureUrl = getRequest().getParameter("mealPictureUrl");
        if (Objects.nonNull(mealPictureUrl) && !mealPictureUrl.isEmpty() && !mealPictureUrl.isBlank()) {
            meal.setPictureUrl(mealPictureUrl);
            return true;
        } else {
            return false;
        }
    }

    private boolean setName(Meal meal){
        final String mealName = getRequest().getParameter("mealName");
        if (Objects.nonNull(mealName) && !mealName.isEmpty() && !mealName.isBlank()) {
            meal.setName(mealName);
            return true;
        } else {
            return false;
        }
    }

}
