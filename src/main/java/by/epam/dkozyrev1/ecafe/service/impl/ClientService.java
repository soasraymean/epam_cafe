package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientRepository;
import by.epam.dkozyrev1.ecafe.entity.Client;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.entity.User;
import by.epam.dkozyrev1.ecafe.service.IClientService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;
import by.epam.dkozyrev1.ecafe.service.factory.impl.EntityServiceFactory;

import java.util.List;
import java.util.Optional;

public class ClientService extends IClientService {

    IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getList() throws ServiceException {
        try{
            final List<Client> clients = clientRepository.getList();
            for (Client client : clients) {
                EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .getList(client.getId()).forEach(client::addOrder);
            }
            return clients;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Client> find(int id) throws ServiceException {
        try{
            final Optional<Client> client = clientRepository.find(id);
            if (client.isPresent()) {
                EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .getList(client.get().getId()).forEach(client.get()::addOrder);
            }
            return client;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Client> find(String name) throws ServiceException {
        try{
            final Optional<Client> client = clientRepository.find(name);
            if (client.isPresent()) {
                EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .getList(client.get().getId()).forEach(client.get()::addOrder);
            }
            return client;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Client> find(User user) throws ServiceException {
        try{
            final Optional<Client> client = clientRepository.find(user);
            if (client.isPresent()) {
                EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .getList(client.get().getId()).forEach(client.get()::addOrder);
            }
            return client;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Client> update(Client client) throws ServiceException {
        try{
            for (Order order : client.getOrders()) {
                EntityServiceFactory
                        .getInstance()
                        .getOrderService()
                        .update(order);
            }
            return clientRepository.update(client);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Client> save(Client client) throws ServiceException {
        try{
            final Optional<Client> savedClient = clientRepository.save(client);
            if(savedClient.isEmpty()){
                throw new ServiceException("Client " + client + " has not been saved");
            }
            return savedClient;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try{
            return clientRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

}