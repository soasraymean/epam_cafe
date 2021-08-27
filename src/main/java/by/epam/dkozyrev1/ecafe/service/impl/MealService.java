package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCompositionRepository;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealRepository;
import by.epam.dkozyrev1.ecafe.entity.Meal;
import by.epam.dkozyrev1.ecafe.service.IMealService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class MealService extends IMealService {


    IMealRepository mealRepository;
    IMealCompositionRepository mealCompositionRepository;

    public MealService(IMealRepository mealRepository, IMealCompositionRepository mealCompositionRepository){
        this.mealRepository = mealRepository;
        this.mealCompositionRepository = mealCompositionRepository;
    }

    @Override
    public List<Meal> getList() throws ServiceException {
        try {
            List<Meal> meals = mealRepository.getList();
            for (Meal meal : meals) {
                mealCompositionRepository.get(meal);
            }
            return meals;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    public List<Meal> getList(int categoryId) throws ServiceException {
        try {
            List<Meal> meals = mealRepository.getList(categoryId);
            for (Meal meal : meals) {
                mealCompositionRepository.get(meal);
            }
            return meals;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Meal> find(int id) throws ServiceException {
        try{
            final Optional<Meal> meal = mealRepository.find(id);
            if (meal.isPresent()) {
                mealCompositionRepository.get(meal.get());
            }
            return meal;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Meal> find(String name) throws ServiceException {
        try{
            final Optional<Meal> meal = mealRepository.find(name);
            if (meal.isPresent()) {
                mealCompositionRepository.get(meal.get());
            }
            return meal;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Meal> update(Meal meal) throws ServiceException {
        try{
            mealRepository.update(meal);
            mealCompositionRepository.update(meal);
            return Optional.of(meal);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Meal> save(Meal meal) throws ServiceException {
        try{
            final Optional<Meal> savedMeal = mealRepository.save(meal);
            if (savedMeal.isEmpty()) {
                throw new ServiceException("Meal " + meal + " has not been saved");
            }
            meal.getIngredients().forEach(savedMeal.get()::addIngredient);
            meal = savedMeal.get();
            mealCompositionRepository.update(meal);
            return Optional.of(meal);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return mealRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

}