package by.epam.dkozyrev1.ecafe.controller.filter;

import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*", filterName = "PagesUrlFilter")
public class PagesUrlFilter implements Filter {

    private static final Set<String> PROCEEDING_URIS = new HashSet<>();

    @Override
    @ExceptionableBeingLogged
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (PROCEEDING_URIS.contains(req.getRequestURI())) {
            req.setAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE, CommonUrlFilter.getCommandType(servletRequest));
            req.getRequestDispatcher(CommonUrlFilter.COMMON_SERVLET_PATH).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        final String contextPath = filterConfig.getServletContext().getContextPath();
        Collections.addAll(PROCEEDING_URIS,
                /* Client page urls */
                contextPath + "/about_cafe",
                contextPath + "/payment",
                contextPath + "/client_order",
                contextPath + "/menu",
                contextPath + "/meal_info",
                contextPath + "/order_info",
                /* Admin page urls */
                contextPath + "/admin_menu",
                contextPath + "/admin_meal_info",
                contextPath + "/admin_categories",
                contextPath + "/admin_ingredients",
                contextPath + "/admin_orders",
                contextPath + "/admin_order_info",
                contextPath + "/admin_clients",
                contextPath + "/admin_clients",
                contextPath + "/admin_client_info",
                contextPath + "/admin_reviews",
                /* Both actors page urls */
                contextPath + "/",
                contextPath + "/home",
                contextPath + "/profile",
                contextPath + "/client_orders",
                contextPath + "/something_went_wrong",
                contextPath + "/attack_answer"
        );
    }

}