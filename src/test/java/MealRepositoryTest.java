package java;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactoryType;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCategoryRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealRepository;
import by.epam.dkozyrev1.ecafe.entity.Category;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class MealRepositoryTest {

    private final Meal savingMeal = new Meal("someMealName", 200,
            new Category("No Category", "noCategoryPic"), "someUrl");

    private static final IMealCategoryRepository MEAL_CATEGORY_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository();
    private static final IMealRepository MEAL_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealRepository();

    @Test
    public void MealRepositoryTest_Save_Meal_Matching_Criteria() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingMeal.getCategory());
        savingMeal.setCategory(savedCategory.orElseThrow());
        final Optional<Meal> savedMeal =
                MEAL_REPOSITORY.save(savingMeal);
        Assert.assertTrue(savedMeal.isPresent());
        MEAL_REPOSITORY.delete(savedMeal.orElseThrow().getId());
        MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void MealRepositoryTest_Save_Meal_Duplicate_Data() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingMeal.getCategory());
        savingMeal.setCategory(savedCategory.orElseThrow());
        final Optional<Meal> savedMeal =
                MEAL_REPOSITORY.save(savingMeal);
        try{
            MEAL_REPOSITORY.save(savingMeal);
        } finally {
            MEAL_REPOSITORY.delete(savedMeal.orElseThrow().getId());
            MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
        }
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Ex_Id() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingMeal.getCategory());
        savingMeal.setCategory(savedCategory.orElseThrow());
        final Optional<Meal> savedMeal =
                MEAL_REPOSITORY.save(savingMeal);
        final Optional<Meal> foundMeal =
                MEAL_REPOSITORY.find(savedMeal.orElseThrow().getId());
        Assert.assertTrue(foundMeal.isPresent());
        MEAL_REPOSITORY.delete(savedMeal.orElseThrow().getId());
        MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Not_Ex_Id() throws DAOException {
        final Optional<Meal> foundMeal =
                MEAL_REPOSITORY.find(-1);
        Assert.assertFalse(foundMeal.isPresent());
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Ex_Name() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingMeal.getCategory());
        savingMeal.setCategory(savedCategory.orElseThrow());
        final Optional<Meal> savedMeal =
                MEAL_REPOSITORY.save(savingMeal);
        final Optional<Meal> foundMeal =
                MEAL_REPOSITORY.find(savedMeal.orElseThrow().getName());
        Assert.assertTrue(foundMeal.isPresent());
        MEAL_REPOSITORY.delete(savedMeal.orElseThrow().getId());
        MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
    }

    @Test
    public void MealRepositoryTest_Find_Meal_Not_Ex_Name() throws DAOException {
        final Optional<Meal> foundMeal =
                MEAL_REPOSITORY.find("invalidName");
        Assert.assertFalse(foundMeal.isPresent());
    }

}
