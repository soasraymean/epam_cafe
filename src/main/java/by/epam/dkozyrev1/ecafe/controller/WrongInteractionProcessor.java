package by.epam.dkozyrev1.ecafe.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class WrongInteractionProcessor {
    private WrongInteractionProcessor(){}
    public static void wrongInteractionProcess(ServletRequest servletRequest, ServletResponse servletResponse)throws IOException{
        ((HttpServletResponse)servletResponse).sendRedirect(servletRequest.getServletContext().getContextPath()+"/something_went_wrong");
    }
}
