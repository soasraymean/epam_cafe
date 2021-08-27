package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.Meal;

import java.util.Optional;

public interface IMealCompositionRepository extends IRelationRepository<Meal> {

    Optional<Meal> get(Meal meal) throws DAOException;
    Optional<Meal> update(Meal meal) throws DAOException;

}