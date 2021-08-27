package by.epam.dkozyrev1.ecafe.controller.command.impl;

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

public class SaveCategoryCommand extends AdminCommand {

    public SaveCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            Category category = new Category();
            final String categoryName = getRequest().getParameter("categoryName");
            if (Objects.nonNull(categoryName) && !categoryName.isEmpty() && !categoryName.isBlank()) {
                category.setName(categoryName);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            final String categoryPictureUrl = getRequest().getParameter("categoryPictureUrl");
            if (Objects.nonNull(categoryPictureUrl) && !categoryPictureUrl.isEmpty() && !categoryPictureUrl.isBlank()) {
                category.setPictureUrl(categoryPictureUrl);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            EntityServiceFactory.getInstance().getMealCategoryService().save(category);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_categories");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
