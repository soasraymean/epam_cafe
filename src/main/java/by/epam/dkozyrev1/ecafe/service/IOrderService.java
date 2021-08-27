package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.exception.UnsupportedKeyTypeException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public abstract class IOrderService implements IEntityService<Order> {

    @Override
    public Optional<Order> findAny(Object key) throws ServiceException {
        return switch (SupportedKeyTypes.of(key.getClass())){
            case INTEGER -> find((Integer) key);
            case STRING -> find((String) key);
            case CLIENT -> find((Client) key);
            default -> throw new UnsupportedKeyTypeException("Unsupported key type " + key.getClass() +
                    " expected " + Integer.class + " or " + String.class + " or " + Client.class);
        };
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract List<Order> getList(int clientId) throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Order> find(int id) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public abstract Optional<Order> find(String clientName) throws ServiceException;
    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    public Optional<Order> find(Client client) throws ServiceException{
        return find(client.getName());
    }

}