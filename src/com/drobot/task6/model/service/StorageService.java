package com.drobot.task6.model.service;

import com.drobot.task6.exception.DaoException;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.dao.impl.BookListDaoImpl;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.validator.BookValidator;
import com.drobot.task6.type.CustomTag;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class StorageService {

    public boolean addBook(String name, int releaseYear, int pages, List<String> authors) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        CustomBook book;
        BookListDao dao;
        boolean result = false;
        if (bookValidator.areFieldsValid(name, releaseYear, pages, authors)) {
            dao = new BookListDaoImpl();
            book = new CustomBook(name, releaseYear, pages, authors);
            try {
                result = dao.add(book);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return result;
    }

    public boolean removeBook(UUID id) throws ServiceException {
        BookListDao dao = new BookListDaoImpl();
        try {
            return dao.remove(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Map<UUID, CustomBook> findById(UUID id) {
        BookListDao dao = new BookListDaoImpl();
        Map<UUID, CustomBook> result;
        Optional<CustomBook> optional = dao.findById(id);
        if (optional.isPresent()) {
            result = Map.of(id, optional.get());
        } else {
            result = Map.of();
        }
        return result;
    }

    public Map<UUID, CustomBook> findByName(String name) {
        BookValidator validator = new BookValidator();
        BookListDao dao;
        Map<UUID, CustomBook> result;
        if (validator.isNameValid(name)) {
            dao = new BookListDaoImpl();
            result = dao.findByName(name);
        } else {
            result = Map.of();
        }
        return result;
    }

    public Map<UUID, CustomBook> findByReleaseYear(int releaseYear) {
        BookValidator validator = new BookValidator();
        BookListDao dao;
        Map<UUID, CustomBook> result;
        if (validator.isReleaseYearValid(releaseYear)) {
            dao = new BookListDaoImpl();
            result = dao.findByReleaseYear(releaseYear);
        } else {
            result = Map.of();
        }
        return result;
    }

    public Map<UUID, CustomBook> findByPages(int pages) {
        BookValidator validator = new BookValidator();
        BookListDao dao;
        Map<UUID, CustomBook> result;
        if (validator.arePagesValid(pages)) {
            dao = new BookListDaoImpl();
            result = dao.findByPages(pages);
        } else {
            result = Map.of();
        }
        return result;
    }

    public Map<UUID, CustomBook> findByAuthors(List<String> authors) {
        BookValidator validator = new BookValidator();
        BookListDao dao;
        Map<UUID, CustomBook> result;
        if (validator.areAuthorsValid(authors)) {
            dao = new BookListDaoImpl();
            result = dao.findByAuthors(authors);
        } else {
            result = Map.of();
        }
        return result;
    }

    public Map<UUID, CustomBook> sort(CustomTag tag) throws ServiceException {
        BookListDao dao = new BookListDaoImpl();
        Map<UUID, CustomBook> result;
        try {
            result = dao.sortByTag(tag.toString());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return result;
    }
}
