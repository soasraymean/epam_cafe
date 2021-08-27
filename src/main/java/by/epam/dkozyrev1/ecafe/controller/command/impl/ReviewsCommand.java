package by.epam.dkozyrev1.ecafe.controller.command.impl;

import by.epam.dkozyrev1.ecafe.controller.command.AdminCommand;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ReviewsCommand extends AdminCommand {

    private static final int ELEMENTS_OF_PAGE = 5;

    public ReviewsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            final String page = getRequest().getParameter("page");
            if(Objects.nonNull(page) && !page.isBlank() && !page.isEmpty() && page.matches("\\d++")) {
                getRequest().setAttribute("comments",
                        EntityServiceFactory.getInstance().getClientCommentService().getList(ELEMENTS_OF_PAGE, Integer.parseInt(page)));
            } else {
                getRequest().setAttribute("comments",
                        EntityServiceFactory.getInstance().getClientCommentService().getList(ELEMENTS_OF_PAGE, 1));
            }
            final int count = EntityServiceFactory.getInstance().getClientCommentService().getCount();
            getRequest().setAttribute("count", count%ELEMENTS_OF_PAGE == 0 ? count/ELEMENTS_OF_PAGE : count/ELEMENTS_OF_PAGE + 1);
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminreviews.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
