package java;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactoryType;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientCommentRepository;
import by.epam.dkozyrev1.ecafe.entity.Comment;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ClientCommentRepositoryTest {

    private final Comment SAVING_COMMENT =
            new Comment("someMessage");

    private static final IClientCommentRepository CLIENT_COMMENT_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientCommentRepository();

    @Test
    public void HallRepositoryTest_Save_Hall_Matching_Criteria() throws DAOException {
        final Optional<Comment> savedComment =
                CLIENT_COMMENT_REPOSITORY.save(SAVING_COMMENT);
        Assert.assertTrue(savedComment.isPresent());
        CLIENT_COMMENT_REPOSITORY.delete(savedComment.orElseThrow().getId());
    }

    @Test
    public void HallRepositoryTest_Find_Ex_Id() throws DAOException {
        final Optional<Comment> savedComment =
                CLIENT_COMMENT_REPOSITORY.save(SAVING_COMMENT);
        final Optional<Comment> foundComment =
                CLIENT_COMMENT_REPOSITORY.find(savedComment.orElseThrow().getId());
        Assert.assertTrue(foundComment.isPresent());
        CLIENT_COMMENT_REPOSITORY.delete(savedComment.orElseThrow().getId());
    }

    @Test
    public void HallRepositoryTest_Find_Not_Ex_Id() throws DAOException {
        final Optional<Comment> notFound =
                CLIENT_COMMENT_REPOSITORY.find(-1);
        Assert.assertFalse(notFound.isPresent());
    }

}
