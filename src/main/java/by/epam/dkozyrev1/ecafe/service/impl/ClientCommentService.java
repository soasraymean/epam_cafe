package by.epam.dkozyrev1.ecafe.service.impl;

import by.epam.dkozyrev1.ecafe.dao.exception.DAOException;
import by.epam.dkozyrev1.ecafe.dao.repo.IClientCommentRepository;
import by.epam.dkozyrev1.ecafe.entity.Comment;
import by.epam.dkozyrev1.ecafe.service.IClientCommentService;
import by.epam.dkozyrev1.ecafe.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientCommentService extends IClientCommentService {

    IClientCommentRepository clientCommentRepository;

    public ClientCommentService(IClientCommentRepository clientCommentRepository) {
        this.clientCommentRepository = clientCommentRepository;
    }

    @Override
    public List<Comment> getList() throws ServiceException {
        try {
            return clientCommentRepository.getList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> getList(int elementsOfPage, int page) throws ServiceException {
        try {
            return clientCommentRepository.getList(elementsOfPage, page);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }



    @Override
    public int getCount() throws ServiceException {
        try {
            return clientCommentRepository.getCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> getList(String clientPhone) throws ServiceException {
        return null;
    }

    @Override
    public Optional<Comment> find(int id) throws ServiceException {
        try {
            return clientCommentRepository.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Comment> find(String authorPhone) throws ServiceException {
        try {
            return clientCommentRepository.find(authorPhone);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Comment> save(Comment comment) throws ServiceException {
        try {
            return clientCommentRepository.save(comment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Comment> update(Comment comment) throws ServiceException {
        try {
            return clientCommentRepository.update(comment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return clientCommentRepository.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
