package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends IEntityRepository<Order> {

    List<Order> getList() throws DAOException;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    List<Order> getList(int clientId) throws DAOException;
    Optional<Order> find(int id) throws DAOException;
    Optional<Order> find(String clientName) throws DAOException;
    Optional<Order> save(Order entity) throws DAOException;
    Optional<Order> update(Order entity) throws DAOException;
    boolean delete(int id) throws DAOException;

}