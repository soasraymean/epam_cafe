package by.epam.dkozyrev1.ecafe.dao.factory.impl;

import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientCommentRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCategoryRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealIngredientRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IUserRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.impl.*;

public class MySQLDAOFactory extends DAOFactory {

    private static MySQLDAOFactory INSTANCE;

    public static MySQLDAOFactory getInstance(){
        if(INSTANCE==null){
            INSTANCE=new MySQLDAOFactory();
        }
        return INSTANCE;
    }

    private MySQLDAOFactory(){}

    @Override
    public IClientRepository getClientRepository() {
        return new ClientRepository();
    }

    @Override
    public IMealCategoryRepository getMealCategoryRepository() {
        return new MealCategoryRepository();
    }

    @Override
    public IMealIngredientRepository getMealIngredientRepository() {
        return new MealIngredientRepository();
    }

    @Override
    public IMealRepository getMealRepository() {
        return new MealRepository();
    }

    @Override
    public IOrderCompositionRepository getOrderCompositionRepository() {
        return new OrderCompositionRepository();
    }

    @Override
    public IOrderRepository getOrderRepository() {
        return new OrderRepository();
    }

    @Override
    public IUserRepository getUserRepository() {
        return new UserRepository();
    }

    @Override
    public IMealCompositionRepository getMealCompositionRepository() {
        return new MealCompositionRepository();
    }

    @Override
    public IClientCommentRepository getClientCommentRepository() {
        return new ClientCommentRepository();
    }
}
