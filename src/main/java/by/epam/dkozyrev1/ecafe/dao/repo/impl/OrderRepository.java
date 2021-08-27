package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderRepository;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository implements IOrderRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.order";
    private static final String SOURCE_TABLE_NAME_ALIAS = " AS o";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"u.id", "username", "password", "email", "phone", "isPromoted",
                    "c.id", "name", "loyalty_points", "bonuses", "isBanned", "o.id",
                    "order_datetime", "isPaid", "isPrepared", "isTaken", "client_mark", "client_comment"};
    private static final String[] JOINING_TABLE_NAMES =
            new String[]{"epam_cafe.client as c", "epam_cafe.user as u"};
    private static final String[] JOIN_FOREIGN_KEY_NAMES =
            new String[]{"o.fk_client_id", "c.fk_user_id"};
    private static final String[] FOREIGN_TABLE_KEY_NAMES =
            new String[]{"c.id", "u.id"};
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{"fk_client_id", "order_datetime", "isPaid", "isPrepared", "isTaken"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id", "fk_client_id", "order_datetime", "isPaid", "isPrepared", "isTaken", "client_mark", "client_comment"};
    private static final String ID_COLUMN_NAME = "o.id";
    private static final String CLIENT_ID_COLUMN_NAME = "c.id";
    private static final String CLIENT_NAME_COLUMN_NAME = "c.name";

    @Override
    public List<Order> getList() throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAMES[0], JOIN_FOREIGN_KEY_NAMES[0], FOREIGN_TABLE_KEY_NAMES[0])
                    .joining(JOINING_TABLE_NAMES[1], JOIN_FOREIGN_KEY_NAMES[1], FOREIGN_TABLE_KEY_NAMES[1])
                    .build(connection)) {
                return getOrders(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Order> getList(int clientId) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAMES[0], JOIN_FOREIGN_KEY_NAMES[0], FOREIGN_TABLE_KEY_NAMES[0])
                    .joining(JOINING_TABLE_NAMES[1], JOIN_FOREIGN_KEY_NAMES[1], FOREIGN_TABLE_KEY_NAMES[1])
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, CLIENT_ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(clientId))) {
                return getOrders(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> find(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAMES[0], JOIN_FOREIGN_KEY_NAMES[0], FOREIGN_TABLE_KEY_NAMES[0])
                    .joining(JOINING_TABLE_NAMES[1], JOIN_FOREIGN_KEY_NAMES[1], FOREIGN_TABLE_KEY_NAMES[1])
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))) {
                return getOrder(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> find(String clientName) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAMES[0], JOIN_FOREIGN_KEY_NAMES[0], FOREIGN_TABLE_KEY_NAMES[0])
                    .joining(JOINING_TABLE_NAMES[1], JOIN_FOREIGN_KEY_NAMES[1], FOREIGN_TABLE_KEY_NAMES[1])
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, CLIENT_NAME_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(clientName))) {
                return getOrder(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> save(Order order) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(order.getCustomer().getId()),
                            Optional.of(order.getOrderDate()),
                            Optional.of(order.isPaid()),
                            Optional.of(order.isReady()),
                            Optional.of(order.isTaken()))) {
                preparedStatement.execute();
                return getCreated();
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Order> update(Order order) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(order.getId()),
                            Optional.of(order.getCustomer().getId()),
                            Optional.of(order.getOrderDate()),
                            Optional.of(order.isPaid()),
                            Optional.of(order.isReady()),
                            Optional.of(order.isTaken()),
                            Optional.of(order.getClientMark()),
                            Optional.of(order.getClientComment()),
                            Optional.of(order.getId()))) {
                preparedStatement.execute();
                return Optional.of(order);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .delete(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))) {
                return preparedStatement.execute();
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    private Optional<Order> getCreated() throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().retrieveConnection()) {
            try (PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME + SOURCE_TABLE_NAME_ALIAS, SELECTION_COLUMN_NAMES)
                    .joining(JOINING_TABLE_NAMES[0], JOIN_FOREIGN_KEY_NAMES[0], FOREIGN_TABLE_KEY_NAMES[0])
                    .joining(JOINING_TABLE_NAMES[1], JOIN_FOREIGN_KEY_NAMES[1], FOREIGN_TABLE_KEY_NAMES[1])
                    .whereMaxId(SOURCE_TABLE_NAME, ID_COLUMN_NAME)
                    .build(connection)) {
                return getOrder(preparedStatement);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    @ExceptionableBeingLogged("Data access object")
    private Optional<Order> getOrder(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.first()) {
                return Optional.empty();
            } else {
                return Optional.of(new Order(
                        resultSet.getInt(12),
                        new Client(new User(
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
                                resultSet.getBoolean(11)),
                        resultSet.getTimestamp(13),
                        resultSet.getBoolean(14),
                        resultSet.getBoolean(15),
                        resultSet.getBoolean(16),
                        resultSet.getInt(17),
                        resultSet.getString(18)
                ));
            }
        }
    }

    @ExceptionableBeingLogged("Data access object")
    private List<Order> getOrders(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (!resultSet.first()) {
                return List.of();
            } else {
                ArrayList<Order> list = new ArrayList<>();
                do {
                    list.add(new Order(
                            resultSet.getInt(12),
                            new Client(new User(
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
                                    resultSet.getBoolean(11)),
                            resultSet.getTimestamp(13),
                            resultSet.getBoolean(14),
                            resultSet.getBoolean(15),
                            resultSet.getBoolean(16),
                            resultSet.getInt(17),
                            resultSet.getString(18)
                    ));
                } while (resultSet.next());
                return List.copyOf(list);
            }
        }
    }


}
