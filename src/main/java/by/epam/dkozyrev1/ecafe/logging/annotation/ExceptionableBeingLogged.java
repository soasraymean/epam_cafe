package by.epam.dkozyrev1.ecafe.logging.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface ExceptionableBeingLogged {
    String value() default ""; /* layer name */
    boolean isFatal() default false;
}