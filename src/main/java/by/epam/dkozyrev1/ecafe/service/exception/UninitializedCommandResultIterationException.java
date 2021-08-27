package by.epam.dkozyrev1.ecafe.service.exception;
public class UninitializedCommandResultIterationException extends ServiceException {
    public UninitializedCommandResultIterationException() {
        super();
    }
    public UninitializedCommandResultIterationException(String message) {
        super(message);
    }
    public UninitializedCommandResultIterationException(String message, Throwable cause) {
        super(message, cause);
    }
    public UninitializedCommandResultIterationException(Throwable cause) {
        super(cause);
    }
}
