package by.epam.dkozyrev1.ecafe.service.exception;

public class ValidationServiceException extends ServiceException {
    public ValidationServiceException() {
        super();
    }
    public ValidationServiceException(String message) {
        super(message);
    }
    public ValidationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValidationServiceException(Throwable cause) {
        super(cause);
    }
}
