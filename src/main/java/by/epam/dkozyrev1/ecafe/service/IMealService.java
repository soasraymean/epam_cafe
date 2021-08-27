package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public abstract class IMealService implements IEntityService<Meal> {

    @Override
    public Optional<Meal> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class);
        };
    }

    @ExceptionableBeingLogged("Service")
    public abstract List<Meal> getList(int categoryId) throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Meal> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Meal> find(String mealName) throws ServiceException;


}