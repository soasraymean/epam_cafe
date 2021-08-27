package java;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactoryType;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealIngredientRepository;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Ingredient;
import by.epam.dkozyrev1.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class IngredientRepositoryTest {

    private final Ingredient savingIngredient = new Ingredient("someName", "someUrl");

    private static final IMealIngredientRepository MEAL_INGREDIENT_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealIngredientRepository();

    @Test
    public void IngredientRepositoryTest_Save_Ingredient_Matching_Criteria() throws DAOException {
        final Optional<Ingredient> savedIngredient =
                MEAL_INGREDIENT_REPOSITORY.save(savingIngredient);
        Assert.assertTrue(savedIngredient.isPresent());
        MEAL_INGREDIENT_REPOSITORY.delete(savedIngredient.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void IngredientRepositoryTest_Save_Ingredient_Duplicate_Data() throws DAOException {
        final Optional<Ingredient> savedIngredient =
                MEAL_INGREDIENT_REPOSITORY.save(savingIngredient);
        try{
            MEAL_INGREDIENT_REPOSITORY.save(savingIngredient);
        } finally {
            MEAL_INGREDIENT_REPOSITORY.delete(savedIngredient.orElseThrow().getId());
        }
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Ex_Id() throws DAOException {
        final Optional<Ingredient> savedIngredient =
                MEAL_INGREDIENT_REPOSITORY.save(savingIngredient);
        final Optional<Ingredient> foundIngredient =
                MEAL_INGREDIENT_REPOSITORY.find(savedIngredient.orElseThrow().getId());
        Assert.assertTrue(foundIngredient.isPresent());
        MEAL_INGREDIENT_REPOSITORY.delete(savedIngredient.orElseThrow().getId());
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Not_Ex_Id() throws DAOException {
        final Optional<Ingredient> foundIngredient =
                MEAL_INGREDIENT_REPOSITORY.find(-1);
        Assert.assertFalse(foundIngredient.isPresent());
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Ex_Name() throws DAOException {
        final Optional<Ingredient> savedIngredient =
                MEAL_INGREDIENT_REPOSITORY.save(savingIngredient);
        final Optional<Ingredient> foundIngredient =
                MEAL_INGREDIENT_REPOSITORY.find(savedIngredient.orElseThrow().getName());
        Assert.assertTrue(foundIngredient.isPresent());
        MEAL_INGREDIENT_REPOSITORY.delete(savedIngredient.orElseThrow().getId());
    }

    @Test
    public void IngredientRepositoryTest_Find_Ingredient_Not_Ex_Name() throws DAOException {
        final Optional<Ingredient> foundIngredient =
                MEAL_INGREDIENT_REPOSITORY.find("invalidName");
        Assert.assertFalse(foundIngredient.isPresent());
    }

}
