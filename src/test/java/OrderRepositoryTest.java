import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactory;
import by.epam.dkozyrev1.ecafe.dao.factory.DAOFactoryType;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IUserRepository;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

public class OrderRepositoryTest {

    private static final Order SAVING_ORDER = new Order(new Client(
            new User("someUsername", "somePassword", "someEmail", "somePhone"),
            "someName"), new Date());

    private static final IUserRepository USER_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getUserRepository();
    private static final IClientRepository CLIENT_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getClientRepository();
    public static final IOrderRepository ORDER_REPOSITORY =
            DAOFactory.getDAOFactory(DAOFactoryType.MySQLDAOFactory).getOrderRepository();

    @Test
    public void OrderRepositoryTest_Save_Order_Matching_Criteria() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(SAVING_ORDER.getCustomer().getUser());
        SAVING_ORDER.getCustomer().setUser(savedUser.orElseThrow());
        final Optional<Client> savedClient =
                CLIENT_REPOSITORY.save(SAVING_ORDER.getCustomer());
        SAVING_ORDER.setCustomer(savedClient.orElseThrow());
        final Optional<Order> savedOrder =
                ORDER_REPOSITORY.save(SAVING_ORDER);
        Assert.assertTrue(savedOrder.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void OrderRepositoryTest_Find_Order_Ex_Id() throws DAOException {
        final Optional<User> savedUser =
                USER_REPOSITORY.save(SAVING_ORDER.getCustomer().getUser());
        SAVING_ORDER.getCustomer().setUser(savedUser.orElseThrow());
        final Optional<Client> savedClient =
                CLIENT_REPOSITORY.save(SAVING_ORDER.getCustomer());
        SAVING_ORDER.setCustomer(savedClient.orElseThrow());
        final Optional<Order> savedOrder =
                ORDER_REPOSITORY.save(SAVING_ORDER);
        final Optional<Order> foundOrder =
                ORDER_REPOSITORY.find(savedOrder.orElseThrow().getId());
        Assert.assertTrue(foundOrder.isPresent());
        USER_REPOSITORY.delete(savedUser.orElseThrow().getId());
    }

    @Test
    public void OrderRepositoryTest_Find_Order_Not_Ex_Id() throws DAOException {
        final Optional<Order> foundOrder =
                ORDER_REPOSITORY.find(-1);
        Assert.assertFalse(foundOrder.isPresent());
    }

}
