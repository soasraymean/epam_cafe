package by.epam.dkozyrev1.ecafe.dao.repo;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.dkozyrev1.ecafe.verification.annotation.CheckedArguments;

import java.util.List;
import java.util.Optional;

public interface IClientRepository extends IEntityRepository<Client>{
    List<Client> getList() throws DAOException;
    Optional<Client> find(int id) throws DAOException;
    Optional<Client> find(String name) throws DAOException;
    @CheckedArguments
    @ExceptionableBeingLogged("Data access object")
    Optional<Client> find(User user) throws DAOException;
    Optional<Client> save(Client client) throws DAOException;
    Optional<Client> update(Client client) throws DAOException;
    boolean delete(int id) throws DAOException;
}
