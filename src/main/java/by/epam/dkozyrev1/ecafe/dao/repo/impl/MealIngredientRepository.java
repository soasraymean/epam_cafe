package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealIngredientRepository;
import by.epam.dkozyrev1.ecafe.entity.Ingredient;
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

public class MealIngredientRepository implements IMealIngredientRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.meal_ingredient";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"id", "name", "pic_url"};
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{ "name", "pic_url"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id", "name", "pic_url"};
    private static final String ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "name";

    @Override
    public List<Ingredient> getList() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<Ingredient> list = new ArrayList<>();
                        do{
                            list.add(new Ingredient(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3)
                            ));
                        } while (resultSet.next());
                        return List.copyOf(list);
                    }
                }
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Ingredient> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(id))){
                return getIngredient(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Ingredient> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, NAME_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(name))){
                return getIngredient(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Ingredient> save(Ingredient ingredient) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(ingredient.getName()),
                            Optional.of(ingredient.getPictureUrl()))){
                preparedStatement.execute();
                return getCreated();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Ingredient> update(Ingredient ingredient) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(ingredient.getId()),
                            Optional.of(ingredient.getName()),
                            Optional.of(ingredient.getPictureUrl()),
                            Optional.of(ingredient.getId()))){
                preparedStatement.execute();
                return Optional.of(ingredient);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete(SOURCE_TABLE_NAME)
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
    private Optional<Ingredient> getCreated() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .whereMaxId(SOURCE_TABLE_NAME)
                    .build(connection)){
                return getIngredient(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<Ingredient> getIngredient(PreparedStatement preparedStatement) throws SQLException{
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new Ingredient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        }
    }

}
