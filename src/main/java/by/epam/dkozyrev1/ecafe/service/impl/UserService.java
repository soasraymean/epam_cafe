package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IUserRepository;
import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.service.IUserService;
import by.epam.dkozyrev1.ecafe.service.authentication.hash.PasswordHashService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService extends IUserService {

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getList() throws ServiceException {
        try{
            return userRepository.getList();
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> find(int id) throws ServiceException {
        try{
            return userRepository.find(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> find(String username) throws ServiceException {
        try{
            return userRepository.find(username);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> update(User user) throws ServiceException {
        try{
            user.setPassword(String.valueOf(PasswordHashService.hash(user.getPassword())));
            return userRepository.update(user);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<User> save(User user) throws ServiceException {
        try{
            final Optional<User> savedUser = userRepository.save(user);
            if (savedUser.isEmpty()) {
                throw new ServiceException("User " + user + " has not been saved");
            }
            return savedUser;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try{
            return userRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}