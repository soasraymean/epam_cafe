package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.config.StaticDataHandler;
import by.epam.dkozyrev1.ecafe.controller.WrongInteractionProcessor;
import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.entity.Category;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UpdateCategoryCommand extends AdminCommand {

    public UpdateCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            final String key = getRequest().getParameter("key");
            if(Objects.nonNull(key) && !key.isEmpty() && !key.isBlank() && key.matches("\\d++")) {
                EntityServiceFactory.getInstance().getMealCategoryService().find(Integer.parseInt(key)).ifPresent(this::update);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    private void update(Category category) {
        try {
            if (setName(category) && setPictureUrl(category)) {
                EntityServiceFactory.getInstance().getMealCategoryService().update(category);
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_categories");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex){
            StaticDataHandler.INSTANCE.getLOGGER().error(String.format("Category %s hasn't been updated cause of %s", category, ex));
        }
    }

    private boolean setName(Category category) {
        final String categoryName = getRequest().getParameter("categoryName");
        if (Objects.nonNull(categoryName) && !categoryName.isEmpty() && !categoryName.isBlank()) {
            category.setName(categoryName);
            return true;
        } else {
            return false;
        }
    }

    private boolean setPictureUrl(Category category) {
        final String categoryPictureUrl = getRequest().getParameter("categoryPictureUrl");
        if (Objects.nonNull(categoryPictureUrl) && !categoryPictureUrl.isEmpty() && !categoryPictureUrl.isBlank()) {
            category.setPictureUrl(categoryPictureUrl);
            return true;
        } else {
            return false;
        }
    }

}
