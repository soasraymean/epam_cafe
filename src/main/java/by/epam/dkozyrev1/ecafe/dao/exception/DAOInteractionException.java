package by.epam.dkozyrev1.ecafe.dao.exception;

public class DAOInteractionException extends DAOException {
    public DAOInteractionException() {
        super();
    }
    public DAOInteractionException(String message) {
        super(message);
    }
    public DAOInteractionException(String message, Throwable cause) {
        super(message, cause);
    }
    public DAOInteractionException(Throwable cause) {
        super(cause);
    }
}
