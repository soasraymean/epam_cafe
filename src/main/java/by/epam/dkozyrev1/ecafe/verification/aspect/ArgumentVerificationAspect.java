package by.epam.dkozyrev1.ecafe.verification.aspect;

import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

@Aspect
public class ArgumentVerificationAspect {

    @Pointcut("@annotation(argumentMeta)")
    private void argumentChecking(CheckedArguments argumentMeta){}

    @Before(
            value = "argumentChecking(argumentMeta)",
            argNames = "jp,argumentMeta")
    public void argumentCheckingProceeding(JoinPoint jp, CheckedArguments argumentMeta) throws IllegalArgumentException {
        if(!Arrays.equals(argumentMeta.requiredArrayOfArgsLength(), new int[]{-1}) &&
                Arrays.stream(argumentMeta.requiredArrayOfArgsLength()).noneMatch(value -> value == Array.getLength(jp.getArgs()[0]) )){
            throw new IllegalArgumentException(
                    "Illegal arguments quantity, required:"
                            + Arrays.toString(argumentMeta.requiredArrayOfArgsLength())
                            + " provided:"
                            + Array.getLength(jp.getArgs()[0]));
        }
        if (!argumentMeta.nullable()) {
            for (Object arg : jp.getArgs()) {
                if(Objects.isNull(arg)){
                    throw new IllegalArgumentException(
                            "Illegal argument "
                                    + null );
                }
            }
        }
    }

}
