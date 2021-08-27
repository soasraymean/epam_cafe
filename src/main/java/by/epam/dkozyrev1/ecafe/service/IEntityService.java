package by.epam.dkozyrev1.ecafe.service;

import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {


    @ExceptionableBeingLogged("Service")
    List<T> getList() throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged
    Optional<T> findAny(Object key) throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> update(T entity) throws ServiceException;

    @CheckedArguments
    @ExceptionableBeingLogged("Service")
    Optional<T> save(T entity) throws ServiceException;

    @ExceptionableBeingLogged("Service")
    boolean delete(int id) throws ServiceException;

}