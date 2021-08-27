package by.epam.dkozyrev1.ecafe.service.exception;

public class UnsupportedCommandTypeException extends ServiceException {
    public UnsupportedCommandTypeException() {
        super();
    }
    public UnsupportedCommandTypeException(String message) {
        super(message);
    }
    public UnsupportedCommandTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnsupportedCommandTypeException(Throwable cause) {
        super(cause);
    }
}
