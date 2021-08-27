package by.epam.dkozyrev1.ecafe.dao.exception;

public class DAOConnectionException extends DAOException{
    public DAOConnectionException() {
        super();
    }

    public DAOConnectionException(String message) {
        super(message);
    }

    public DAOConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOConnectionException(Throwable cause) {
        super(cause);
    }
}
