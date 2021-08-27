package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IUserRepository;
import by.epam.dkozyrev1.ecafe.entity.User;
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

public class UserRepository implements IUserRepository {
    private static final String SOURCE_TABLE_NAME = "epam_cafe.user";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"id", "username", "password", "email", "phone", "isPromoted"};
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"username", "password", "email", "phone"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id", "username", "password", "email", "phone", "isPromoted"};
    private static final String ID_COLUMN_NAME = "id";
    private static final String USERNAME_COLUMN_NAME = "username";

    @Override
    public List<User> getList() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<User> list = new ArrayList<>();
                        do{
                            list.add(new User(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getBoolean(6)
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
    public Optional<User> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getUser(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<User> find(String username) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, USERNAME_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(username))){
                return getUser(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<User> save(User user) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(user.getUsername()),
                            Optional.of(user.getPassword()),
                            Optional.of(user.getEmail()),
                            Optional.of(user.getPhone()))){
                preparedStatement.execute();
                return getCreated();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<User> update(User user) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(user.getId()),
                            Optional.of(user.getUsername()),
                            Optional.of(user.getPassword()),
                            Optional.of(user.getEmail()),
                            Optional.of(user.getPhone()),
                            Optional.of(user.isPromoted()),
                            Optional.of(user.getId()))){
                preparedStatement.execute();
                return Optional.of(user);
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
    private Optional<User> getCreated() throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .whereMaxId(SOURCE_TABLE_NAME)
                    .build(connection)) {
                return getUser(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<User> getUser(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else{
                return Optional.of(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6)));
            }
        }
    }
}
