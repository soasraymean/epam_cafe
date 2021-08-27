package by.epam.dkozyrev1.ecafe.verification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface CheckedArguments {
    boolean nullable() default false;
    int[] requiredArrayOfArgsLength() default {-1};
}
