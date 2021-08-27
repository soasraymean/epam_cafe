package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IEntityRepository<T> {

    @ExceptionableBeingLogged("Data access object")
    List<T> getList() throws DAOException;

    @ExceptionableBeingLogged("Data access object")
    Optional<T> find(int id) throws DAOException;

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> find(String name) throws DAOException;

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> save(T entity) throws DAOException;

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> update(T entity) throws DAOException;

    @ExceptionableBeingLogged("Data access object")
    boolean delete(int id) throws DAOException;
}
