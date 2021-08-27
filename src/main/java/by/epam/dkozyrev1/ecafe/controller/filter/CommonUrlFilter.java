package by.epam.dkozyrev1.ecafe.controller.filter;

import by.epam.dkozyrev1.ecafe.controller.command.WebCommandType;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*", filterName = "CommonUrlFilter")
public class CommonUrlFilter implements Filter {

    private static final Set<String> PROCEEDING_URIS = new HashSet<>();
    public static final String COMMON_SERVLET_PATH = "/app";
    public static final String COMMAND_ATTRIBUTE = "command";

    @Override
    @ExceptionableBeingLogged
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (PROCEEDING_URIS.contains(req.getRequestURI())) {
            req.setAttribute(COMMAND_ATTRIBUTE, getCommandType(servletRequest));
            req.getRequestDispatcher(COMMON_SERVLET_PATH).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        final String contextPath = filterConfig.getServletContext().getContextPath();
        Collections.addAll( PROCEEDING_URIS,
                /* Client common urls */
                contextPath + "/add_meal_to_order",
                contextPath + "/remove_meal_from_order",
                contextPath + "/place_order",
                contextPath + "/cancel_order",
                contextPath + "/rate_order",
                contextPath + "/reserve_hall",
                contextPath + "/leave_comment",
                contextPath + "/payment",
                contextPath + "/payment_success",
                contextPath + "/change_profile",
                contextPath + "/add_ingredient",
                contextPath + "/remove_ingredient",
                /* Admin common urls */
                contextPath + "/save_category",
                contextPath + "/save_ingredient",
                contextPath + "/save_meal",
                contextPath + "/save_hall",
                contextPath + "/update_category",
                contextPath + "/update_ingredient",
                contextPath + "/update_meal",
                contextPath + "/update_hall",
                contextPath + "/update_order",
                contextPath + "/update_client",
                contextPath + "/delete_category",
                contextPath + "/delete_ingredient",
                contextPath + "/delete_meal",
                contextPath + "/delete_hall",
                contextPath + "/delete_reservation",
                contextPath + "/change_admin_profile"
        );
    }

    static WebCommandType getCommandType(ServletRequest request) {
        if(((HttpServletRequest)request).getRequestURI().equals(((HttpServletRequest) request).getContextPath()+"/")){
            return WebCommandType.HOME_COMMAND;
        }
        return WebCommandType.valueOf(
                ((HttpServletRequest) request).getRequestURI()
                        .substring(request.getServletContext().getContextPath().length())
                        .replace("/", "")
                        .concat("_command")
                        .toUpperCase());
    }

}
