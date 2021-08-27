package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IMealCategoryRepository;
import by.epam.dkozyrev1.ecafe.entity.Category;
import by.epam.dkozyrev1.ecafe.service.IMealCategoryService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class MealCategoryService extends IMealCategoryService {


    IMealCategoryRepository mealCategoryRepository;

    public MealCategoryService(IMealCategoryRepository mealCategoryRepository){
        this.mealCategoryRepository = mealCategoryRepository;
    }

    @Override
    public List<Category> getList() throws ServiceException {
        try{
            return mealCategoryRepository.getList();
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Category> find(int id) throws ServiceException {
        try{
            return mealCategoryRepository.find(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Category> find(String name) throws ServiceException {
        try{
            return mealCategoryRepository.find(name);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Category> update(Category category) throws ServiceException {
        try{
            return mealCategoryRepository.update(category);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Optional<Category> save(Category category) throws ServiceException {
        try{
            final Optional<Category> savedCategory = mealCategoryRepository.save(category);
            if(savedCategory.isEmpty()){
                throw new ServiceException("Category " + category + " has not been saved");
            }
            return savedCategory;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try{
            return mealCategoryRepository.delete(id);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}