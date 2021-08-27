package by.epam.dkozyrev1.ecafe.logging.aspect;

import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.Objects;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(annotation)")
    private void exceptionableMethodToLog(ExceptionableBeingLogged annotation){ }

    @Around(
            value = "exceptionableMethodToLog(annotation)",
            argNames = "jp,annotation")
    public Object aroundThrowablePointcutProcessing(ProceedingJoinPoint jp, ExceptionableBeingLogged annotation) throws Exception {
        try{
            if(Objects.isNull(jp))
                throw new Exception("Null join point");
            return jp.proceed();
        } catch (Throwable ex){
            logger.error(jp.toLongString()  + " at " + jp.getSourceLocation() + " " + annotation.value()  + " calling with args{" + Arrays.toString(jp.getArgs()) + "} failed with " + (annotation.isFatal() ? "fatal " : "exception ") + ex);
            throw new Exception(ex);
        }
    }

}
