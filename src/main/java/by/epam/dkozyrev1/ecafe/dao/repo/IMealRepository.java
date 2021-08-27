package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;

import java.util.List;
import java.util.Optional;

public interface IMealRepository extends IEntityRepository<Meal> {

    List<Meal> getList() throws DAOException;
    @ExceptionableBeingLogged("Data access object")
    List<Meal> getList(int categoryId) throws DAOException;
    Optional<Meal> find(int id) throws DAOException;
    Optional<Meal> find(String name) throws DAOException;
    Optional<Meal> save(Meal meal) throws DAOException;
    Optional<Meal> update(Meal meal) throws DAOException;
    boolean delete(int id) throws DAOException;

}