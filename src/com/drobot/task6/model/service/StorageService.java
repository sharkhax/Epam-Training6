package com.drobot.task6.model.service;

import com.drobot.task6.type.CustomTag;
import com.drobot.task6.exception.BookException;
import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.dao.impl.BookListDaoImpl;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.validator.BookValidator;
import com.drobot.task6.parser.BookParser;

import java.util.ArrayList;
import java.util.List;

public class StorageService {

    private static final String BOOK_EXISTS_EXCEPTION_MESSAGE = "The book is already stored";
    private static final String NO_BOOK_EXCEPTION_MESSAGE = "The book wasn't found";
    private static final String INVALID_VALUE_EXCEPTION_MESSAGE = "Invalid value";
    private static final String INCORRECT_TAG_EXCEPTION_MESSAGE = "Incorrect tag";

    public boolean addBook(String name, int releaseYear, int pages, List<String> authors) throws BookException {
        BookValidator bookValidator = new BookValidator();
        boolean result;

        if (bookValidator.areFieldsValid(name, releaseYear, pages, authors)) {
            BookListDao dao = new BookListDaoImpl();
            CustomBook book = new CustomBook(name, releaseYear, pages, authors);

            if (!dao.contains(book)) {
                result = dao.addBook(book);
            } else {
                throw new BookException(BOOK_EXISTS_EXCEPTION_MESSAGE);
            }
        } else {
            result = false;
        }
        return result;
    }

    public boolean removeBook(long id) throws BookException {
        BookListDao dao = new BookListDaoImpl();
        CustomBook book;

        try {
            book = dao.findById(id);
        } catch (BookException e) {
            throw new BookException(NO_BOOK_EXCEPTION_MESSAGE);
        }
        return dao.removeBook(book);
    }

    public List<CustomBook> findByTag(CustomTag tag, String stringValue) throws BookException {
        BookValidator validator = new BookValidator();
        BookListDao dao = new BookListDaoImpl();
        BookParser parser = new BookParser();

        switch (tag) {
            case ID -> {
                long id = parser.parseLong(stringValue);
                List<CustomBook> result = new ArrayList<>();
                result.add(dao.findById(id));

                return result;
            }
            case NAME -> {
                if (validator.isNameValid(stringValue)) {
                    return dao.findByName(stringValue);
                } else {
                    throw new BookException(INVALID_VALUE_EXCEPTION_MESSAGE);
                }
            }
            case RELEASE_YEAR -> {
                int releaseYear = parser.parseInt(stringValue);

                if (validator.isReleaseYearValid(releaseYear)) {
                    return dao.findByReleaseYear(releaseYear);
                } else {
                    throw new BookException(INVALID_VALUE_EXCEPTION_MESSAGE);
                }
            }
            case PAGES -> {
                int pages = parser.parseInt(stringValue);

                if (validator.arePagesValid(pages)) {
                    return dao.findByPages(pages);
                } else {
                    throw new BookException(INVALID_VALUE_EXCEPTION_MESSAGE);
                }
            }
            case AUTHOR -> {
                if (validator.isAuthorValid(stringValue)) {
                    return dao.findByAuthor(stringValue);
                } else {
                    throw new BookException(INVALID_VALUE_EXCEPTION_MESSAGE);
                }
            } default -> throw new BookException(INCORRECT_TAG_EXCEPTION_MESSAGE);
        }
    }

    public List<CustomBook> sortByTag(CustomTag tag) throws BookException {
        BookListDao dao = new BookListDaoImpl();
        List<CustomBook> result;

        switch (tag) {
            case ID -> result = dao.sortBooksById();
            case NAME -> result = dao.sortBooksByName();
            case RELEASE_YEAR -> result = dao.sortBooksByReleaseYear();
            case PAGES -> result = dao.sortBooksByPages();
            case AUTHOR -> result = dao.sortBooksByAuthor();
            default -> throw new BookException(INCORRECT_TAG_EXCEPTION_MESSAGE);
        }
        return result;
    }
}
