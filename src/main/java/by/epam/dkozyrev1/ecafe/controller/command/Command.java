package by.epam.dkozyrev1.ecafe.controller.command;

import by.epam.dkozyrev1.ecafe.controller.command.impl.*;
import by.epam.dkozyrev1.ecafe.controller.exception.ControllerException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class Command {

    private final ServletRequest request;
    private final ServletResponse response;

    public Command(ServletRequest request, ServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public abstract void executeGet()throws ControllerException;
    public abstract void executePost()throws ControllerException;

    public ServletRequest getRequest() {
        return request;
    }

    public ServletResponse getResponse() {
        return response;
    }

    public static Command of(WebCommandType webCommandType, ServletRequest request, ServletResponse response){
        return switch (webCommandType){
            case HOME_COMMAND -> new HomeCommand(request, response);
            case PROFILE_COMMAND -> new ProfileCommand(request, response);
            case SIGN_IN_COMMAND -> new SignInCommand(request, response);
            case SIGN_UP_COMMAND -> new SignUpCommand(request, response);
            case SIGN_OUT_COMMAND -> new SignOutCommand(request, response);
            case CHANGE_PROFILE_COMMAND -> new ChangeProfileCommand(request, response);
            case MENU_COMMAND -> new MenuCommand(request, response);
            case ADMIN_MEAL_INFO_COMMAND -> new AdminMealInfoCommand(request, response);
            case ADMIN_CATEGORIES_COMMAND -> new AdminCategoriesCommand(request, response);
            case ADD_MEAL_TO_ORDER_COMMAND -> new AddMealToOrderCommand(request, response);
            case GET_LOCAL_IMAGE_COMMAND -> new GetLocalImage(request, response);
            case REMOVE_MEAL_FROM_ORDER_COMMAND -> new RemoveMealFromOrderCommand(request, response);
            case MEAL_INFO_COMMAND -> new MealInfoCommand(request, response);
            case ORDER_INFO_COMMAND -> new OrderInfoCommand(request, response);
            case CLIENT_ORDER_COMMAND -> new ClientOrderCommand(request, response);
            case CLIENT_ORDERS_COMMAND -> new ClientOrdersCommand(request, response);
            case PAYMENT_COMMAND -> new PaymentCommand(request, response);
            case PLACE_ORDER_COMMAND -> new PlaceOrderCommand(request, response);
            case ABOUT_CAFE_COMMAND -> new AboutCafeCommand(request, response);
            case ADMIN_ORDER_INFO_COMMAND -> new AdminOrderInfoCommand(request, response);
            case ADMIN_MENU_COMMAND -> new AdminMenuCommand(request, response);
            case ADMIN_ORDERS_COMMAND -> new AdminOrdersCommand(request, response);
            case ADMIN_INGREDIENTS_COMMAND -> new AdminIngredientsCommand(request, response);
            case ADMIN_CLIENTS_COMMAND -> new AdminClientsCommand(request, response);
            case ADMIN_CLIENT_INFO_COMMAND -> new AdminClientInfo(request, response);
            case UPDATE_MEAL_COMMAND -> new UpdateMealCommand(request, response);
            case UPDATE_INGREDIENT_COMMAND -> new UpdateIngredientCommand(request, response);
            case UPDATE_CLIENT_COMMAND -> new UpdateClientCommand(request, response);
            case UPDATE_ORDER_COMMAND -> new UpdateOrderCommand(request, response);
            case ADD_INGREDIENT_COMMAND -> new AddIngredientToMealCommand(request, response);
            case REMOVE_INGREDIENT_COMMAND -> new RemoveIngredientFromMealCommand(request, response);
            case UPDATE_CATEGORY_COMMAND -> new UpdateCategoryCommand(request, response);
            case SAVE_MEAL_COMMAND -> new SaveMealCommand(request, response);
            case SAVE_CATEGORY_COMMAND -> new SaveCategoryCommand(request, response);
            case SAVE_INGREDIENT_COMMAND -> new SaveIngredientCommand(request, response);
            case DELETE_MEAL_COMMAND -> new DeleteMealCommand(request, response);
            case DELETE_CATEGORY_COMMAND -> new DeleteCategoryCommand(request, response);
            case DELETE_INGREDIENT_COMMAND -> new DeleteIngredientCommand(request, response);
            case CANCEL_ORDER_COMMAND -> new DeleteOrderCommand(request, response);
            case CHANGE_ADMIN_PROFILE_COMMAND -> new ChangeAdminProfileCommand(request, response);
            case PAYMENT_SUCCESS_COMMAND -> new PaymentSuccessCommand(request, response);
            case LEAVE_COMMENT_COMMAND -> new LeaveCommentCommand(request, response);
            case RATE_ORDER_COMMAND -> new RateOrderCommand(request, response);
            case ADMIN_REVIEWS_COMMAND -> new ReviewsCommand(request, response);
            case SOMETHING_WENT_WRONG_COMMAND -> new SomethingWentWrongCommand(request, response);
            case ATTACK_ANSWER_COMMAND -> new AttackAnswerCommand(request, response);
        };

    }
}
