package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientRepository;
import by.epam.dkozyrev1.ecafe.entity.Client;
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
import java.util.Objects;
import java.util.Optional;

public class ClientRepository implements IClientRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.client";
    private static final String SOURCE_TABLE_NAME_ALIAS = " AS c";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"u.id", "username", "password", "email", "phone", "isPromoted",
                    "c.id", "name", "loyalty_points", "bonuses", "isBanned"};
    private static final String JOINING_TABLE_NAME = "epam_cafe.user AS u";
    private static final String JOIN_FOREIGN_KEY_NAME = "c.fk_user_id";
    private static final String FOREIGN_TABLE_KEY_NAME = "u.id";
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"name", "fk_user_id"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id", "name", "loyalty_points", "bonuses", "isBanned", "fk_user_id"};
    private static final String ID_COLUMN_NAME = "c.id";
    private static final String USER_ID_COLUMN_NAME = "u.id";
    private static final String NAME_COLUMN_NAME = "c.name";

    @Override
    public List<Client> getList() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .build(connection)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()){
                        return List.of();
                    } else{
                        ArrayList<Client> list = new ArrayList<>();
                        do{
                            list.add(new Client(new User(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getBoolean(6)),
                                    resultSet.getInt(7),
                                    resultSet.getString(8),
                                    resultSet.getInt(9),
                                    resultSet.getInt(10),
                                    resultSet.getBoolean(11)));
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
    public Optional<Client> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getClient(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> find(String name) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, NAME_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(name))){
                return getClient(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> find(User user) throws DAOException {
        if(Objects.isNull(user)) return Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, USER_ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(user.getId()))){
                return getClient(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> save(Client client) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(client.getName()),
                            Optional.of(client.getUser().getId())
                    )){
                preparedStatement.execute();
                return getCreated();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Client> update(Client client) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(client.getId()),
                            Optional.of(client.getName()),
                            Optional.of(client.getLoyaltyPoints()),
                            Optional.of(client.getBonuses()),
                            Optional.of(client.isBanned()),
                            Optional.of(client.getUser().getId()),
                            Optional.of(client.getId())
                    )){
                preparedStatement.execute();
                return Optional.of(client);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private Optional<Client> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAME, JOIN_FOREIGN_KEY_NAME, FOREIGN_TABLE_KEY_NAME)
                    .whereMaxId(SOURCE_TABLE_NAME, ID_COLUMN_NAME)
                    .build(connection)){
                return getClient(preparedStatement);
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

    @NotNull
    @ExceptionableBeingLogged("Data access object")
    private Optional<Client> getClient(PreparedStatement preparedStatement) throws SQLException{
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.first()) {
                return Optional.empty();
            } else {
                return Optional.of(new Client(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBoolean(6)),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10),
                        resultSet.getBoolean(11)));
            }
        }
    }

}
