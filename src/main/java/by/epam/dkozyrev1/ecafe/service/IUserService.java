package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.Optional;

public abstract class IUserService implements IEntityService<User> {

    @Override
    public Optional<User> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class);
        };
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<User> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<User> find(String username) throws ServiceException;

}