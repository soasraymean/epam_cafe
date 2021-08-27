package by.epam.dkozyrev1.ecafe.config;

import java.lang.reflect.InvocationTargetException;

public class DependenciesLoader {

    private static final DependenciesLoader DEPENDENCIES_LOADER_INSTANCE = new DependenciesLoader();

    public static DependenciesLoader getInstance() {
        return DEPENDENCIES_LOADER_INSTANCE;
    }

    private DependenciesLoader() {
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            throw new DependenciesInitializationError(e);
        }
    }

}