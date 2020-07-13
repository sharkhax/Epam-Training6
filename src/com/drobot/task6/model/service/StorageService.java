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

    private static final String INVALID_VALUE_EXCEPTION_MESSAGE = "Invalid value";
    private static final String INCORRECT_TAG_EXCEPTION_MESSAGE = "Invalid tag";

    public boolean addBook(String name, int releaseYear, int pages, List<String> authors) throws ServiceException {
        BookValidator bookValidator = new BookValidator();
        CustomBook book;
        BookListDao dao;
        boolean result;

        if (bookValidator.areFieldsValid(name, releaseYear, pages, authors)) {
            dao = new BookListDaoImpl();
            book = new CustomBook(name, releaseYear, pages, authors);

            try {
                result = dao.addBook(book);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeBook(UUID id) throws ServiceException {
        BookListDao dao = new BookListDaoImpl();
        CustomBook book;
        Optional<CustomBook> optional;
        boolean result;

        try {
            optional = dao.findById(id);
            if (optional.isPresent()) {
                book = optional.get();
                result = dao.removeBook(book);
            } else {
                result = false;
            }
            return result;
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

    public Map<UUID, CustomBook> findByName(String name) throws ServiceException {
        BookValidator validator = new BookValidator();
        BookListDao dao;

        if (validator.isNameValid(name)) {
            dao = new BookListDaoImpl();
            return dao.findByName(name);
        } else {
            throw new ServiceException(INVALID_VALUE_EXCEPTION_MESSAGE);
        }
    }

    public Map<UUID, CustomBook> findByReleaseYear(int releaseYear) throws ServiceException {
        BookValidator validator = new BookValidator();
        BookListDao dao;

        if (validator.isReleaseYearValid(releaseYear)) {
            dao = new BookListDaoImpl();
            return dao.findByReleaseYear(releaseYear);
        } else {
            throw new ServiceException(INVALID_VALUE_EXCEPTION_MESSAGE);
        }
    }

    public Map<UUID, CustomBook> findByPages(int pages) throws ServiceException {
        BookValidator validator = new BookValidator();
        BookListDao dao;

        if (validator.arePagesValid(pages)) {
            dao = new BookListDaoImpl();
            return dao.findByPages(pages);
        } else {
            throw new ServiceException(INVALID_VALUE_EXCEPTION_MESSAGE);
        }
    }

    public Map<UUID, CustomBook> findByAuthors(List<String> authors) throws ServiceException {
        BookValidator validator = new BookValidator();
        BookListDao dao;

        if (validator.areAuthorsValid(authors)) {
            dao = new BookListDaoImpl();
            return dao.findByAuthors(authors);
        } else {
            throw new ServiceException(INVALID_VALUE_EXCEPTION_MESSAGE);
        }
    }

    public Map<UUID, CustomBook> sort(CustomTag tag) throws ServiceException {
        BookListDao dao = new BookListDaoImpl();
        Map<UUID, CustomBook> result;

        switch (tag) {
            case ID -> result = dao.sortBooksById();
            case NAME -> result = dao.sortBooksByName();
            case RELEASE_YEAR -> result = dao.sortBooksByReleaseYear();
            case PAGES -> result = dao.sortBooksByPages();
            case AUTHORS -> result = dao.sortBooksByAuthor();
            default -> throw new ServiceException(INCORRECT_TAG_EXCEPTION_MESSAGE);
        }
        return result;
    }
}
