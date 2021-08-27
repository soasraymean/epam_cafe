package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealRepository;
import by.epam.dkozyrev1.ecafe.entity.Category;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MealRepository implements IMealRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.meal";
    private static final String SOURCE_TABLE_NAME_ALIAS = " AS m";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"c.id", "c.name", "c.pic_url",
                    "m.id", "m.name", "m.price", "m.pic_url"};
    private static final String JOINING_TABLE_NAME = "epam_cafe.meal_category as c";
    private static final String JOIN_FOREIGN_KEY_NAME = "m.fk_category_id";
    private static final String FOREIGN_TABLE_KEY_NAME = "c.id";
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"name", "price", "pic_url", "fk_category_id"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id", "name", "price", "pic_url", "fk_category_id"};
    private static final String CATEGORY_ID_COLUMN_NAME = "c.id";
    private static final String ID_COLUMN_NAME = "m.id";
    private static final String NAME_COLUMN_NAME = "m.name";

    @Override
    public List<Meal> getList() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .build(connection)){
                return getMeals(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Meal> getList(int categoryId) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, CATEGORY_ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(categoryId))){
                return getMeals(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getMeal(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, NAME_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(name))){
                return getMeal(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> save(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(meal.getName()),
                            Optional.of(meal.getPrice()),
                            Optional.of(meal.getPictureUrl()),
                            Optional.of(meal.getCategory().getId()))){
                preparedStatement.execute();
                return getCreated();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Meal> update(Meal meal) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(meal.getId()),
                            Optional.of(meal.getName()),
                            Optional.of(meal.getPrice()),
                            Optional.of(meal.getPictureUrl()),
                            Optional.of(meal.getCategory().getId()),
                            Optional.of(meal.getId()))){
                preparedStatement.execute();
                return Optional.of(meal);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return preparedStatement.execute();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private Optional<Meal> getCreated() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .whereMaxId(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, ID_COLUMN_NAME)
                    .build(connection)){
                return getMeal(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<Meal> getMeal(PreparedStatement preparedStatement) throws SQLException{
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Meal(
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
            }
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private List<Meal> getMeals(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return List.of();
            } else{
                ArrayList<Meal> list = new ArrayList<>();
                do{
                    list.add(new Meal(
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
                return List.copyOf(list);
            }
        }
    }


}
