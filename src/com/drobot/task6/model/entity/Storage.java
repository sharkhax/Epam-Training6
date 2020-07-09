package com.drobot.task6.model.entity;

import com.drobot.task6.exception.BookException;
import com.drobot.task6.model.service.IdService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {

    private static final int CAPACITY = 200;
    private static final String NULL_EXCEPTION_MESSAGE = "Book is null";
    private static final String CONTAINED_EXCEPTION_MESSAGE = "Book is already contained";
    private static final String NOT_CONTAIN_EXCEPTION_MESSAGE = "The storage doesnt contain the book";

    private static Storage INSTANCE;
    private final List<CustomBook> booksList;

    private Storage() {
        booksList = new ArrayList<>(CAPACITY);
    }

    public static Storage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public List<CustomBook> getBooksList() {
        return Collections.unmodifiableList(booksList);
    }

    public boolean addBook(CustomBook book) throws BookException {
        if (book == null) {
            throw new BookException(NULL_EXCEPTION_MESSAGE);
        }

        if (booksList.contains(book)) {
            throw new BookException(CONTAINED_EXCEPTION_MESSAGE);
        }

        boolean result;

        if (booksList.size() == CAPACITY) {
            result = false;
        } else {
            IdService idService = new IdService();
            long id = idService.generateId();
            book.setId(id);
            result = booksList.add(book);
        }

        return result;
    }

    public boolean removeBook(CustomBook book) throws BookException {
        if (book == null) {
            throw new BookException(NULL_EXCEPTION_MESSAGE);
        }

        if (!booksList.contains(book)) {
            throw new BookException(NOT_CONTAIN_EXCEPTION_MESSAGE);
        }

        book.setId(-1);
        return booksList.remove(book);
    }
}
