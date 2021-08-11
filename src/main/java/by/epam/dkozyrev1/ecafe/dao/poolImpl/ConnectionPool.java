package by.epam.dkozyrev1.ecafe.dao.poolImpl;

import by.epam.dkozyrev1.ecafe.config.DAOConfiguration;
import by.epam.dkozyrev1.ecafe.dao.IConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOConnectionPoolRaisingException;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConnectionPool implements IConnectionPool {

    private static ConnectionPool INSTANCE;
    private final List<Connection> availableConnections = new CopyOnWriteArrayList<>();
    private final Set<Connection> involvedConnections = new CopyOnWriteArraySet<>();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    @Override
    public Connection retrieveConnection() throws DAOException {
        Connection connection = null;
        if (!availableConnections.isEmpty()) {
            connection = availableConnections.get(0);
            if (Objects.nonNull(connection)) {
                availableConnections.remove(0);
                involvedConnections.add(connection);
            }
        } else if (availableConnections.size() + involvedConnections.size() < DAOConfiguration.getInstance().getMaxPoolSize()) {
            raiseConnections(DAOConfiguration.getInstance().getPoolIncreaseStep());
        }
        return connection;
    }

    private void raiseConnections(int initialCapacity) throws DAOConnectionPoolRaisingException {
        for (int i = 0; i < initialCapacity; i++) {
            try {
                availableConnections.add(new ConnectionProxy(DriverManager.getConnection(
                        DAOConfiguration.getInstance().getDbUrl(),
                        DAOConfiguration.getInstance().getDbUser(),
                        DAOConfiguration.getInstance().getDbPassword())));
            } catch (SQLException exception) {
                throw new DAOConnectionPoolRaisingException(exception);
            }
        }
    }

    public boolean isInvolved(Connection connection) {
        return involvedConnections.contains(connection);
    }

    public void shutdown() {
        involvedConnections.forEach(e -> {
            try {
                e.close();
            } catch (SQLException exception) {

            }
        });
        availableConnections.forEach(e -> {
            try {
                ((ConnectionProxy) e).shutdown();
            } catch (SQLException exception) {

            }
        });
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        if (Objects.nonNull(connection) && involvedConnections.contains(connection)) {
            try {
                involvedConnections.remove(connection);
                availableConnections.add(connection);
            } catch (Exception exception) {
                return false;
            }
        }
        return true;
    }
}
