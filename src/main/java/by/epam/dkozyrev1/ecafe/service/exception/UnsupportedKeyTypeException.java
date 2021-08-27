package by.epam.dkozyrev1.ecafe.service.exception;


public class UnsupportedKeyTypeException extends ServiceException {
    public UnsupportedKeyTypeException() {
        super();
    }
    public UnsupportedKeyTypeException(String message) {
        super(message);
    }
    public UnsupportedKeyTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    public UnsupportedKeyTypeException(Throwable cause) {
        super(cause);
    }
}
