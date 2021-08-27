package by.epam.dkozyrev1.ecafe.service.exception;

public class ParameterRequiredException extends ServiceException {
    public ParameterRequiredException() {
        super();
    }
    public ParameterRequiredException(String message) {
        super(message);
    }
    public ParameterRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
    public ParameterRequiredException(Throwable cause) {
        super(cause);
    }
}
