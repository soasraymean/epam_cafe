package java;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactoryType;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCategoryRepository;
import by.epam.dkozyrev1.ecafe.entity.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CategoryRepositoryTest {

    private final Category savingCategory = new Category("someName", "someUrl");
    private static final IMealCategoryRepository MEAL_CATEGORY_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getMealCategoryRepository();

    @Test
    public void CategoryRepositoryTest_Save_Category_Matching_Criteria() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingCategory);
        Assert.assertTrue(savedCategory.isPresent());
        MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
    }

    @Test(expected = DAOException.class)
    public void CategoryRepositoryTest_Save_Category_Duplicate_Data() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingCategory);
        try {
            MEAL_CATEGORY_REPOSITORY.save(savingCategory);
        } finally {
            MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
        }
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Ex_Id() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingCategory);
        final Optional<Category> foundCategory =
                MEAL_CATEGORY_REPOSITORY.find(savedCategory.orElseThrow().getId());
        Assert.assertTrue(foundCategory.isPresent());
        MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Not_Ex_Id() throws DAOException {
        final Optional<Category> foundCategory =
                MEAL_CATEGORY_REPOSITORY.find(-1);
        Assert.assertFalse(foundCategory.isPresent());
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Ex_Name() throws DAOException {
        final Optional<Category> savedCategory =
                MEAL_CATEGORY_REPOSITORY.save(savingCategory);
        final Optional<Category> foundCategory =
                MEAL_CATEGORY_REPOSITORY.find(savedCategory.orElseThrow().getName());
        Assert.assertTrue(foundCategory.isPresent());
        MEAL_CATEGORY_REPOSITORY.delete(savedCategory.orElseThrow().getId());
    }

    @Test
    public void CategoryRepositoryTest_Find_Category_Not_Ex_Name() throws DAOException {
        final Optional<Category> foundCategory =
                MEAL_CATEGORY_REPOSITORY.find("invalidName");
        Assert.assertFalse(foundCategory.isPresent());
    }

}
