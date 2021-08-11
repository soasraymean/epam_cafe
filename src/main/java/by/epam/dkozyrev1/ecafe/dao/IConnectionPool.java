package by.epam.dkozyrev1.ecafe.dao;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;

import java.sql.Connection;

public interface IConnectionPool {
    Connection retrieveConnection() throws DAOException;
    boolean releaseConnection(Connection connection);
}
