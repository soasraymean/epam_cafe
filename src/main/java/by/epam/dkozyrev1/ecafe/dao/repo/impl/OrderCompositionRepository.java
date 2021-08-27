package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderCompositionRepository;
import by.epam.dkozyrev1.ecafe.entity.Category;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderCompositionRepository implements IOrderCompositionRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.order_composition";
    private static final String SOURCE_TABLE_ALIAS = " AS o";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"c.id", "c.name", "c.pic_url",
                    "m.id", "meal_name", "meal_price", "m.pic_url"};
    private static final String[] JOINING_TABLE_NAMES =
            new String[]{"epam_cafe.meal  AS m", "epam_cafe.meal_category  AS c"};
    private static final String[] JOIN_FOREIGN_KEY_NAMES =
            new String[]{"o.fk_meal_id", "m.fk_category_id"};
    private static final String[] FOREIGN_TABLE_KEY_NAMES =
            new String[]{"m.id", "c.id"};
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"fk_order_id", "fk_meal_id", "meal_name", "meal_price"};
    private static final String ID_COLUMN_NAME = "fk_order_id";

    @Override
    public Optional<Order> get(Order order) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAMES[0], JOIN_FOREIGN_KEY_NAMES[0], FOREIGN_TABLE_KEY_NAMES[0])
                    .joining( JOINING_TABLE_NAMES[1], JOIN_FOREIGN_KEY_NAMES[1], FOREIGN_TABLE_KEY_NAMES[1])
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(order.getId()))){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.first()) {
                        do {
                            order.addMeal(new Meal(
                                    resultSet.getInt(4),
                                    resultSet.getString(5),
                                    resultSet.getInt(6),
                                    new Category(
                                            resultSet.getInt(1),
                                            resultSet.getString(2),
                                            resultSet.getString(3)
                                    ),
                                    resultSet.getString(7)
                            ));
                        } while (resultSet.next());
                    }
                    return Optional.of(order);
                }
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> update(Order order) throws DAOException {
        if(clear(order))
            throw new DAOException("Order " + order + " can not be updated");
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .beginBatch(connection)
                    .addBatch(generateBatch(order))
                    .endBatch()){
                preparedStatement.executeBatch();
                return Optional.of(order);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private boolean clear(Order order) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete(SOURCE_TABLE_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(order.getId()))){
                return preparedStatement.execute();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private ArrayList<Optional<?>[]> generateBatch(Order order){
        ArrayList<Optional<?>[]> optionalArrayList = new ArrayList<>();
        for (Meal meal : order.getMeals()) {
            optionalArrayList.add(new Optional[]{Optional.of(order.getId()),Optional.of(meal.getId()), Optional.of(meal.getName()), Optional.of(meal.getPrice())});
        }
        return optionalArrayList;
    }

}
