package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.Order;

import java.util.Optional;

public interface IOrderCompositionRepository extends IRelationRepository<Order> {

    Optional<Order> get(Order order) throws DAOException;
    Optional<Order> update(Order order) throws DAOException;

}