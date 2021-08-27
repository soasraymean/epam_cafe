package by.epam.dkozyrev1.ecafe.service.factory;

import by.epam.dkozyrev1.ecafe.service.*;

public interface IEntityServiceFactory {
    IUserService getUserService();
    IClientService getClientService();
    IMealCategoryService getMealCategoryService();
    IMealService getMealService();
    IMealIngredientService getMealIngredientService();
    IOrderService getOrderService();
    IClientCommentService getClientCommentService();

}
