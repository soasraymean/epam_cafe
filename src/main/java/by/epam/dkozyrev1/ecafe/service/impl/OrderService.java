package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IOrderRepository;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.entity.Order;
import by.epam.dkozyrev1.ecafe.service.IOrderService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderService extends IOrderService {
    IOrderRepository orderRepository;
    IOrderCompositionRepository orderCompositionRepository;
    IMealCompositionRepository mealCompositionRepository;

    public OrderService(IOrderRepository orderRepository, IOrderCompositionRepository orderCompositionRepository, IMealCompositionRepository mealCompositionRepository){
        this.orderRepository = orderRepository;
        this.orderCompositionRepository = orderCompositionRepository;
        this.mealCompositionRepository = mealCompositionRepository;
    }

    @Override
    public List<Order> getList() throws ServiceException {
        try {
            final List<Order> orders = orderRepository.getList();
            for (Order order : orders) {
                orderCompositionRepository.get(order);
                for (Meal meal : order.getMeals()) {
                    mealCompositionRepository.get(meal);
                }
            }
            return orders;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Order> getList(int clientId) throws ServiceException {
        try {
            final List<Order> orders = orderRepository.getList(clientId);
            for (Order order : orders) {
                orderCompositionRepository.get(order);
                for (Meal meal : order.getMeals()) {
                    mealCompositionRepository.get(meal);
                }
            }
            return orders;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Order> find(int id) throws ServiceException {
        try {
            final Optional<Order> order = orderRepository.find(id);
            if (order.isPresent()) {
                orderCompositionRepository.get(order.get());
                for (Meal meal : order.get().getMeals()) {
                    mealCompositionRepository.get(meal);
                }
            }
            return order;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Order> find(String clientName) throws ServiceException {
        try {
            final Optional<Order> order = orderRepository.find(clientName);
            if (order.isPresent()) {
                orderCompositionRepository.get(order.get());
                for (Meal meal : order.get().getMeals()) {
                    mealCompositionRepository.get(meal);
                }
            }
            return order;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Order> update(Order order) throws ServiceException {
        try {
            orderRepository.update(order);
            orderCompositionRepository.update(order);
            return Optional.of(order);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Order> save(Order order) throws ServiceException {
        try {
            final Optional<Order> savedOrder = orderRepository.save(order);
            if(savedOrder.isEmpty()){
                throw new ServiceException("Order " + order + " has not been saved");
            }
            order.getMeals().forEach(savedOrder.get()::addMeal);
            order = savedOrder.get();
            orderCompositionRepository.update(order);
            return Optional.of(order);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try{
            return orderRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}
