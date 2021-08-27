package by.epam.dkozyrev1.ecafe.controller.security;

import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public final class RequestScriptingFilter {
    private RequestScriptingFilter() {
    }

    private static final String SCRIPT_REGULAR = ".*<.*>.*</.*>.*";

    public static boolean filter(ServletRequest servletRequest) {
        AtomicBoolean noAttack = new AtomicBoolean(true);
        servletRequest.getParameterMap().forEach(((key, values) -> {
            if (Arrays.stream(values).anyMatch(value -> value.matches(SCRIPT_REGULAR))) {
                noAttack.set(false);
            }
        }));
        servletRequest.getAttributeNames().asIterator().forEachRemaining(key -> {
            if (servletRequest.getAttribute(key).toString().matches(SCRIPT_REGULAR)) {
                noAttack.set(false);
            }
        });
        return noAttack.get();
    }
}
