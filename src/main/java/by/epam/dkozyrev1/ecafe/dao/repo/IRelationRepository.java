package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.Optional;

public interface IRelationRepository<T> {

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> get(T entity) throws DAOException;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<T> update(T entity) throws DAOException;

}
