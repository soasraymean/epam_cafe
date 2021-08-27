package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.entity.Ingredient;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.Optional;

public abstract class IMealIngredientService implements IEntityService<Ingredient> {

    @Override
    public Optional<Ingredient> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class);
        };
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Ingredient> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Ingredient> find(String mealName) throws ServiceException;

}