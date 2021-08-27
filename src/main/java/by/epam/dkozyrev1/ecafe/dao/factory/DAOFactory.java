package by.epam.dkozyrev1.ecafe.dao.factory;

import by.epam.dkozyrev1.ecafe.dao.factory.impl.MySQLDAOFactory;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientCommentRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCategoryRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealIngredientRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IUserRepository;

public abstract class DAOFactory {

    public abstract IClientRepository getClientRepository();
    public abstract IMealCategoryRepository getMealCategoryRepository();
    public abstract IMealIngredientRepository getMealIngredientRepository();
    public abstract IMealRepository getMealRepository();
    public abstract IOrderCompositionRepository getOrderCompositionRepository();
    public abstract IOrderRepository getOrderRepository();
    public abstract IUserRepository getUserRepository();
    public abstract IMealCompositionRepository getMealCompositionRepository();
    public abstract IClientCommentRepository getClientCommentRepository();

    public static DAOFactory getDAOFactory(DAOFactoryType daoFactoryType){
        return switch (daoFactoryType) {
            case MySQLDAOFactory -> MySQLDAOFactory.getInstance();
            default -> throw new IllegalStateException("Unexpected value: " + daoFactoryType);
        };
    }

}