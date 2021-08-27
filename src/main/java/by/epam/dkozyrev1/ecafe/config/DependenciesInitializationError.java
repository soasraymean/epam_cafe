package by.epam.dkozyrev1.ecafe.config;

public class DependenciesInitializationError extends Error {
    public DependenciesInitializationError() {
        super();
    }
    public DependenciesInitializationError(String message) {
        super(message);
    }
    public DependenciesInitializationError(String message, Throwable cause) {
        super(message, cause);
    }
    public DependenciesInitializationError(Throwable cause) {
        super(cause);
    }
}