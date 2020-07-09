package com.drobot.task6.model.dao.impl;

import com.drobot.task6.model.comparator.*;
import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.exception.BookException;
import com.drobot.task6.model.entity.Storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookListDaoImpl implements BookListDao {

    private final static String NOT_FOUND_EXCEPTION_MESSAGE = "No elements found by ";
    private final static String ID = "id: ";
    private final static String NAME = "name: ";
    private final static String RELEASE_YEAR = "release year: ";
    private final static String PAGES = "pages: ";
    private final static String AUTHOR = "author: ";

    private final Storage storage = Storage.getInstance();

    @Override
    public boolean addBook(CustomBook book) throws BookException {
        return storage.addBook(book);
    }

    @Override
    public boolean removeBook(CustomBook book) throws BookException {
        return storage.removeBook(book);
    }

    @Override
    public CustomBook findById(long id) throws BookException {
        List<CustomBook> bookList = storage.getBooksList();

        for (CustomBook book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new BookException(NOT_FOUND_EXCEPTION_MESSAGE + ID + id);
    }

    @Override
    public List<CustomBook> findByName(String name) throws BookException {
        List<CustomBook> bookList = storage.getBooksList();
        List<CustomBook> resultList = new ArrayList<>();

        for (CustomBook book : bookList) {
            if (book.getName().equals(name)) {
                resultList.add(book);
            }
        }

        if (resultList.isEmpty()) {
            throw new BookException(NOT_FOUND_EXCEPTION_MESSAGE + NAME + name);
        } else {
            return resultList;
        }
    }

    @Override
    public List<CustomBook> findByReleaseYear(int releaseYear) throws BookException {
        List<CustomBook> bookList = storage.getBooksList();
        List<CustomBook> resultList = new ArrayList<>();

        for (CustomBook book : bookList) {
            if (book.getReleaseYear() == releaseYear) {
                resultList.add(book);
            }
        }

        if (resultList.isEmpty()) {
            throw new BookException(NOT_FOUND_EXCEPTION_MESSAGE + RELEASE_YEAR + releaseYear);
        } else {
            return resultList;
        }
    }

    @Override
    public List<CustomBook> findByPages(int pages) throws BookException {
        List<CustomBook> bookList = storage.getBooksList();
        List<CustomBook> resultList = new ArrayList<>();

        for (CustomBook book : bookList) {
            if (book.getPages() == pages) {
                resultList.add(book);
            }
        }

        if (resultList.isEmpty()) {
            throw new BookException(NOT_FOUND_EXCEPTION_MESSAGE + PAGES + pages);
        } else {
            return resultList;
        }
    }

    @Override
    public List<CustomBook> findByAuthor(String author) throws BookException {
        List<CustomBook> bookList = storage.getBooksList();
        List<CustomBook> resultList = new ArrayList<>();

        for (CustomBook book : bookList) {
            List<String> authors = book.getAuthors();

            if (authors.contains(author)) {
                resultList.add(book);
            }
        }

        if (resultList.isEmpty()) {
            throw new BookException(NOT_FOUND_EXCEPTION_MESSAGE + AUTHOR + author);
        } else {
            return resultList;
        }
    }

    @Override
    public List<CustomBook> sortBooksById() {
        List<CustomBook> bookList = storage.getBooksList();
        Comparator<CustomBook> comparator = new BookIdComparator();
        bookList.sort(comparator);

        return bookList;
    }

    @Override
    public List<CustomBook> sortBooksByName() {
        List<CustomBook> bookList = storage.getBooksList();
        Comparator<CustomBook> comparator = new BookNameComparator();
        bookList.sort(comparator);

        return bookList;
    }

    @Override
    public List<CustomBook> sortBooksByReleaseYear() {
        List<CustomBook> bookList = storage.getBooksList();
        Comparator<CustomBook> comparator = new BookReleaseYearComparator();
        bookList.sort(comparator);

        return bookList;
    }

    @Override
    public List<CustomBook> sortBooksByPages() {
        List<CustomBook> bookList = storage.getBooksList();
        Comparator<CustomBook> comparator = new BookPagesComparator();
        bookList.sort(comparator);

        return bookList;
    }

    @Override
    public List<CustomBook> sortBooksByAuthor() {
        List<CustomBook> bookList = storage.getBooksList();
        Comparator<CustomBook> comparator = new BookAuthorComparator();
        bookList.sort(comparator);

        return bookList;
    }

    @Override
    public boolean contains(CustomBook book) {
        List<CustomBook> books = storage.getBooksList();
        return books.contains(book);
    }
}
