package by.epam.dkozyrev1.ecafe.service.exception;

public class UserAuthenticationServiceException extends ServiceException {
    public UserAuthenticationServiceException() {
        super();
    }
    public UserAuthenticationServiceException(String message) {
        super(message);
    }
    public UserAuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserAuthenticationServiceException(Throwable cause) {
        super(cause);
    }
}
