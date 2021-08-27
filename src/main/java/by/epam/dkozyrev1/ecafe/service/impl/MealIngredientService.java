package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealIngredientRepository;
import by.epam.dkozyrev1.ecafe.entity.Ingredient;
import by.epam.dkozyrev1.ecafe.service.IMealIngredientService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class MealIngredientService extends IMealIngredientService {


    IMealIngredientRepository mealIngredientRepository;

    public MealIngredientService(IMealIngredientRepository mealIngredientRepository){
        this.mealIngredientRepository = mealIngredientRepository;
    }

    @Override
    public List<Ingredient> getList() throws ServiceException {
        try {
            return mealIngredientRepository.getList();
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Ingredient> find(int id) throws ServiceException {
        try {
            return mealIngredientRepository.find(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Ingredient> find(String name) throws ServiceException {
        try {
            return mealIngredientRepository.find(name);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Ingredient> update(Ingredient ingredient) throws ServiceException {
        try {
            return mealIngredientRepository.update(ingredient);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Ingredient> save(Ingredient ingredient) throws ServiceException {
        try {
            final Optional<Ingredient> savedIngredient = mealIngredientRepository.save(ingredient);
            if(savedIngredient.isEmpty()){
                throw new ServiceException("Ingredient " + ingredient + " has not been saved");
            }
            return savedIngredient;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return mealIngredientRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

}