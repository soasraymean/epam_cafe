package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IMealIngredientRepository extends IEntityRepository<Ingredient> {

    List<Ingredient> getList() throws DAOException;
    Optional<Ingredient> find(int id) throws DAOException;
    Optional<Ingredient> find(String name) throws DAOException;
    Optional<Ingredient> save(Ingredient ingredient) throws DAOException;
    Optional<Ingredient> update(Ingredient ingredient) throws DAOException;
    boolean delete(int id) throws DAOException;

}