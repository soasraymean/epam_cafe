package by.epam.dkozyrev1.ecafe.service.factory.impl;

import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactoryType;
import by.epam.dkozyrev1.ecafe.service.IClientCommentService;
import by.epam.dkozyrev1.ecafe.service.IClientService;
import by.epam.dkozyrev1.ecafe.service.IMealCategoryService;
import by.epam.dkozyrev1.ecafe.service.IMealIngredientService;
import by.epam.dkozyrev1.ecafe.service.IMealService;
import by.epam.dkozyrev1.ecafe.service.IOrderService;
import by.epam.dkozyrev1.ecafe.service.IUserService;
import by.epam.dkozyrev1.ecafe.service.factory.IEntityServiceFactory;
import by.epam.dkozyrev1.ecafe.service.impl.*;

public class EntityServiceFactory implements IEntityServiceFactory {

    private final DAOFactory daoFactory;

    private EntityServiceFactory(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    public static EntityServiceFactory getInstance(){
        return EntityServiceFactoryInstanceHandler.ENTITY_SERVICE_FACTORY_INSTANCE;
    }

    @Override
    public IUserService getUserService() {
        return new UserService(
                daoFactory.getUserRepository());
    }

    @Override
    public IClientService getClientService() {
        return new ClientService(
                daoFactory.getClientRepository());
    }

    @Override
    public IMealCategoryService getMealCategoryService() {
        return new MealCategoryService(
                daoFactory.getMealCategoryRepository());
    }

    @Override
    public IMealService getMealService() {
        return new MealService(
                daoFactory.getMealRepository(),
                daoFactory.getMealCompositionRepository());
    }

    @Override
    public IMealIngredientService getMealIngredientService() {
        return new MealIngredientService(
                daoFactory.getMealIngredientRepository());
    }

    @Override
    public IOrderService getOrderService() {
        return new OrderService(
                daoFactory.getOrderRepository(),
                daoFactory.getOrderCompositionRepository(),
                daoFactory.getMealCompositionRepository());
    }

    @Override
    public IClientCommentService getClientCommentService() {
        return new ClientCommentService(daoFactory.getClientCommentRepository());
    }


    private static class EntityServiceFactoryInstanceHandler{

        private static final EntityServiceFactory ENTITY_SERVICE_FACTORY_INSTANCE =
                new EntityServiceFactory(DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory));

    }

}
