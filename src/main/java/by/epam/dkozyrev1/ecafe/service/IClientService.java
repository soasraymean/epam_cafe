package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.Optional;

public abstract class IClientService implements IEntityService<Client> {

    @Override
    public Optional<Client> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            case USER -> find((User) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class + " or " + User.class);
        };
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Client> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Client> find(String name) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Client> find(User user) throws ServiceException;

}