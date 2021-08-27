package by.epam.dkozyrev1.ecafe.dao.repo.impl;

import by.epam.dkozyrev1.ecafe.dao.builder.PreparedStatementBuilder;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.Limiter;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LimiterMapGeneration;
import by.epam.dkozyrev1.ecafe.dao.builder.limiter.LogicConcatenator;
import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.poolImpl.ConnectionPool;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientCommentRepository;
import by.epam.dkozyrev1.ecafe.entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientCommentRepository implements IClientCommentRepository {

    private static final String SOURCE_TABLE_NAME = "epam_cafe.client_comment";
    private static final String[] SELECTION_COLUMN_NAMES =
            new String[]{"id","author_name","author_phone","message"};
    private static final String[] INSERTION_COLUMN_NAMES =
            new String[]{ "author_name","author_phone","message"};
    private static final String[] UPDATING_COLUMN_NAMES =
            new String[]{"id","author_name","author_phone","message"};
    private static final String ID_COLUMN_NAME = "id";

    @Override
    public List<Comment> getList() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .build(connection)){
                return getComments(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public List<Comment> getList(int elementsOfPage, int page) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .paging(elementsOfPage, page)
                    .build(connection)){
                return getComments(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }


    @Override
    public Optional<Comment> find(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection, Optional.of(id))){
                return getComment(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Comment> find(String string) throws DAOException {
        return Optional.empty();
    }


    @Override
    public Optional<Comment> save(Comment comment) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .insert(SOURCE_TABLE_NAME, INSERTION_COLUMN_NAMES)
                    .build(connection,
                            Optional.of(comment.getMessage()))){
                preparedStatement.execute();
                return getCreated();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    private Optional<Comment> getCreated() throws DAOException{
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .select(SOURCE_TABLE_NAME, SELECTION_COLUMN_NAMES)
                    .whereMaxId(SOURCE_TABLE_NAME)
                    .build(connection)){
                return getComment(preparedStatement);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public Optional<Comment> update(Comment comment) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .update(SOURCE_TABLE_NAME, UPDATING_COLUMN_NAMES)
                    .where(LimiterMapGeneration.generateOfSingleType(Limiter.EQUALS, ID_COLUMN_NAME), LogicConcatenator.AND)
                    .build(connection,
                            Optional.of(comment.getId()),
                            Optional.of(comment.getMessage()),
                            Optional.of(comment.getId()))){
                preparedStatement.execute();
                return Optional.of(comment);
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public int getCount() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().retrieveConnection()){
            try(PreparedStatement preparedStatement = new PreparedStatementBuilder()
                    .count(SOURCE_TABLE_NAME, ID_COLUMN_NAME)
                    .build(connection)){
                try (final ResultSet resultSet = preparedStatement.executeQuery()){
                    if(!resultSet.first()) {
                        return -1;
                    } else {
                        return resultSet.getInt(1);
                    }
                }
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
                    .build(connection,Optional.of(id))){
                return preparedStatement.execute();
            }
        } catch (SQLException ex){
            throw new DAOException(ex);
        }
    }


    private List<Comment> getComments(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return List.of();
            } else{
                ArrayList<Comment> list = new ArrayList<>();
                do{
                    list.add(new Comment(
                            resultSet.getInt(1),
                            resultSet.getString(2)
                    ));
                } while (resultSet.next());
                return List.copyOf(list);
            }
        }
    }

    private Optional<Comment> getComment(PreparedStatement preparedStatement) throws SQLException {
        try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(!resultSet.first()){
                return Optional.empty();
            } else {
                return Optional.of(new Comment(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        }
    }

}