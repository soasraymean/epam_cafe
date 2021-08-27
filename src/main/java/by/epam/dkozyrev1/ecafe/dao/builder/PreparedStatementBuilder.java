package by.epam.dkozyrev1.ecafe.dao.builder;

import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOPreparedStatementBuilderException;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PreparedStatementBuilder {

    private final StringBuilder query;

    private PreparedStatement batchPreparedStatement;

    public StringBuilder getQuery() {
        return query;
    }

    public PreparedStatementBuilder() {
        query = new StringBuilder();
    }

    @CheckedArguments
    public PreparedStatementBuilder select(String tableName, String... selectingItems) {
        query.append("SELECT ");
        for (int i = 0; i < selectingItems.length; i++) {
            query.append(selectingItems[i])
                    .append(i == selectingItems.length-1? " ":"?");
        }
        query.append("FROM ")
                .append(tableName)
                .append(" ");
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder count(String tableName, String countingItemName){
        query.append("SELECT COUNT(")
                .append(countingItemName)
                .append(") ")
                .append("FROM ")
                .append(tableName)
                .append(" ");
        return this;
    }
    @CheckedArguments
    public PreparedStatementBuilder insert(String tableName, String... columnNames){
        query.append("INSERT INTO ")
                .append(tableName)
                .append(" ");
        if(Objects.nonNull(columnNames)&&columnNames.length>0){
            query.append("(");
            for (int i = 0; i < columnNames.length; i++) {
                query.append(columnNames[i])
                        .append(i == columnNames.length - 1 ? ") " : ", ");
            }
            query.append("VALUES(");
            for (int i = 0; i < columnNames.length; i++) {
                query.append("?")
                        .append(i == columnNames.length - 1 ? ") " : ", ");
            }
        }
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder update(String tableName, String... columnNames){
        query.append("UPDATE ")
                .append(tableName)
                .append(" ");
        if(Objects.nonNull(columnNames)&&columnNames.length>0){
            query.append("SET ");
            for (int i = 0; i < columnNames.length; i++) {
                query.append(columnNames[i])
                        .append("=?")
                        .append(i == columnNames.length - 1 ? " " : ", ");
            }
        }
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder delete(String tableName){
        query.append("DELETE FROM ").append(tableName).append(" ");
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder where(Map<String, Limiter> limiterHashMap, LogicConcatenator logicConcatenator){
        if(Objects.nonNull(limiterHashMap)&&limiterHashMap.size()>0){
            query.append(query.toString().contains("WHERE") ? "AND " : "WHERE ");
            AtomicInteger counter = new AtomicInteger(0);
            limiterHashMap.forEach((columnName, limiter)->
                    query.append(limiter.isReversed() ? "NOT " : "")
                            .append(columnName).append(limiter)
                            .append("? ")
                            .append(counter.getAndIncrement() == limiterHashMap.size()-1 ? "" : logicConcatenator + " "));
        }
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder whereMaxId(String tableName, String idAlias){
        query.append(query.toString().contains("WHERE") ? "AND " : "WHERE ")
                .append(idAlias)
                .append("=(SELECT MAX(id) FROM ")
                .append(tableName)
                .append(") ");
        return this;
    }
    @CheckedArguments
    public PreparedStatementBuilder whereMaxId(String tableName){
        query.append(query.toString().contains("WHERE") ? "AND " : "WHERE ")
                .append("id")
                .append("=(SELECT MAX(id) FROM ").append(tableName)
                .append(") ");
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder paging(int elementsOfPage, int pageNum){
        query.append("LIMIT ")
                .append(elementsOfPage)
                .append(" ")
                .append(pageNum - 1 > 0 ?
                        "OFFSET " + ((pageNum-1) * elementsOfPage) : "");
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder joining(String foreignTable, String joinTableKey, String foreignTableKey){
        query.append("JOIN ")
                .append(foreignTable)
                .append(" ON ")
                .append(joinTableKey)
                .append("=")
                .append(foreignTableKey)
                .append(" ");
        return this;
    }

    @CheckedArguments
    public PreparedStatementBuilder beginBatch(Connection connection, Optional<?>... args) throws DAOPreparedStatementBuilderException {
        try {
            if(Objects.nonNull(args)&&args.length>0) {
                batchPreparedStatement = build(connection, args);
                batchPreparedStatement.addBatch();
            } else {
                batchPreparedStatement = build(connection);
            }
            return this;
        } catch (SQLException ex){
            throw new DAOPreparedStatementBuilderException(ex);
        }
    }

    public PreparedStatementBuilder addBatch(Optional<?>... args) throws DAOPreparedStatementBuilderException {
        try {
            int i = 0;
            for (Optional<?> arg : args) {
                if (i <= batchPreparedStatement.getParameterMetaData().getParameterCount()) {
                    batchPreparedStatement.setObject(++i, arg.orElse(null));
                }
            }
            batchPreparedStatement.addBatch();
            return this;
        } catch (SQLException ex){
            throw new DAOPreparedStatementBuilderException(ex);
        }
    }

    public PreparedStatementBuilder addBatch(List<Optional<?>[]> batchArgs) throws DAOPreparedStatementBuilderException {
        try {
            for (Optional<?>[] args : batchArgs) {
                int i = 0;
                for(Optional<?>  arg : args) {
                    if (i <= batchPreparedStatement.getParameterMetaData().getParameterCount()) {
                        batchPreparedStatement.setObject(++i, arg.orElse(null));
                    }
                }
                batchPreparedStatement.addBatch();
            }
            return this;
        } catch (SQLException ex){
            throw new DAOPreparedStatementBuilderException(ex);
        }
    }

    public PreparedStatement endBatch() {
        return batchPreparedStatement;
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    public PreparedStatement build(Connection connection) throws DAOPreparedStatementBuilderException {
        try {
            if(connection.isClosed()){
                throw new DAOPreparedStatementBuilderException("Connection must not be null or closed");
            }
            return connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex){
            throw new DAOPreparedStatementBuilderException(ex);
        }
    }

    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    public PreparedStatement build(Connection connection, Optional<?>...args) throws DAOPreparedStatementBuilderException {
        try {
            if(connection.isClosed()){
                throw new DAOPreparedStatementBuilderException("Connection must not be null or closed");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            int i=0;
            for (Optional<?> arg : args) {
                if(i<=preparedStatement.getParameterMetaData().getParameterCount()) {
                    preparedStatement.setObject(++i, arg.orElse(null));
                }
            }
            return preparedStatement;
        } catch (SQLException ex){
            throw new DAOPreparedStatementBuilderException(ex);
        }
    }
}
