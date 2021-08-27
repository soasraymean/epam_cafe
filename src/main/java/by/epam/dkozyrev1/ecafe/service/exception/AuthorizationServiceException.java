package by.epam.dkozyrev1.ecafe.service.exception;

public class AuthorizationServiceException extends ServiceException {

    public AuthorizationServiceException() {
        super();
    }
    public AuthorizationServiceException(String message) {
        super(message);
    }
    public AuthorizationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public AuthorizationServiceException(Throwable cause) {
        super(cause);
    }

}
