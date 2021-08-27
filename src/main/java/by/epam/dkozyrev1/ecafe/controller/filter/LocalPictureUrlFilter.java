package by.epam.dkozyrev1.ecafe.controller.filter;

import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/get_local_image")
public class LocalPictureUrlFilter implements Filter {
    private FilterConfig config = null;
    private boolean active = false;
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void destroy() {
        config = null;
    }

    @Override
    @ExceptionableBeingLogged
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(active) {
            servletRequest.setAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE, CommonUrlFilter.getCommandType(servletRequest));
            servletRequest.getRequestDispatcher(CommonUrlFilter.COMMON_SERVLET_PATH).forward(servletRequest, servletResponse);
        }
    }

}