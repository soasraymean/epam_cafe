package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IEntityRepository<User> {

    List<User> getList() throws DAOException;
    Optional<User> find(int id) throws DAOException;
    Optional<User> find(String username) throws DAOException;
    Optional<User> save(User user) throws DAOException;
    Optional<User> update(User user) throws DAOException;
    boolean delete(int id) throws DAOException;

}