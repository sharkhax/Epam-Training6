package com.drobot.task6.model.dao.impl;

import com.drobot.task6.exception.DaoException;
import com.drobot.task6.model.comparator.BookIdByAuthorComparator;
import com.drobot.task6.model.comparator.BookIdByNameComparator;
import com.drobot.task6.model.comparator.BookIdByPagesComparator;
import com.drobot.task6.model.comparator.BookIdByReleaseYearComparator;
import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.entity.Storage;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

public class BookListDaoImpl implements BookListDao {

    private static final String NULL_EXCEPTION_MESSAGE = "Book is null";
    private static final String CONTAINED_EXCEPTION_MESSAGE = "Book is already contained";
    private static final String NOT_CONTAIN_EXCEPTION_MESSAGE = "The storage doesnt contain the book";

    private final Storage storage = Storage.getInstance();

    @Override
    public boolean addBook(CustomBook book) throws DaoException {
        if (book == null) {
            throw new DaoException(NULL_EXCEPTION_MESSAGE);
        }

        if (storage.containsValue(book)) {
            throw new DaoException(CONTAINED_EXCEPTION_MESSAGE);
        }

        return storage.addBook(book);
    }

    @Override
    public boolean removeBook(CustomBook book) throws DaoException {
        if (book == null) {
            throw new DaoException(NULL_EXCEPTION_MESSAGE);
        }

        UUID id = book.getId();

        if (!storage.containsKey(id)) {
            throw new DaoException(NOT_CONTAIN_EXCEPTION_MESSAGE);
        }

        return storage.removeBook(id);
    }

    @Override
    public Optional<CustomBook> findById(UUID id) {
        Optional<CustomBook> result;

        if (storage.containsKey(id)) {
            Map<UUID, CustomBook> booksMap = storage.getBooksMap();
            CustomBook book = booksMap.get(id);
            result = Optional.of(book);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> findByName(String name) {
        List<CustomBook> booksList = storage.getBooksList();
        Map<UUID, CustomBook> result = new HashMap<>();

        for (CustomBook book : booksList) {
            if (book.getName().equals(name)) {
                result.put(book.getId(), book);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> findByReleaseYear(int releaseYear) {
        List<CustomBook> bookList = storage.getBooksList();
        Map<UUID, CustomBook> result = new HashMap<>();

        for (CustomBook book : bookList) {
            if (book.getReleaseYear() == releaseYear) {
                result.put(book.getId(), book);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> findByPages(int pages) {
        List<CustomBook> bookList = storage.getBooksList();
        Map<UUID, CustomBook> result = new HashMap<>();

        for (CustomBook book : bookList) {
            if (book.getPages() == pages) {
                result.put(book.getId(), book);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> findByAuthors(List<String> authors) {
        List<CustomBook> bookList = storage.getBooksList();
        Map<UUID, CustomBook> result = new HashMap<>();

        for (CustomBook book : bookList) {
            List<String> actualAuthors = book.getAuthors();

            if (actualAuthors.containsAll(authors)) {
                result.put(book.getId(), book);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> sortBooksById() {
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        return new TreeMap<>(booksMap);
    }

    @Override
    public Map<UUID, CustomBook> sortBooksByName() {
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        Comparator<UUID> comparator = new BookIdByNameComparator();
        Map<UUID, CustomBook> result = new TreeMap<>(comparator);
        result.putAll(booksMap);

        return result;
    }

    @Override
    public Map<UUID, CustomBook> sortBooksByReleaseYear() {
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        Comparator<UUID> comparator = new BookIdByReleaseYearComparator();
        Map<UUID, CustomBook> result = new TreeMap<>(comparator);
        result.putAll(booksMap);

        return result;
    }

    @Override
    public Map<UUID, CustomBook> sortBooksByPages() {
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        Comparator<UUID> comparator = new BookIdByPagesComparator();
        Map<UUID, CustomBook> result = new TreeMap<>(comparator);
        result.putAll(booksMap);

        return result;
    }

    @Override
    public Map<UUID, CustomBook> sortBooksByAuthor() {
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        Comparator<UUID> comparator = new BookIdByAuthorComparator();
        Map<UUID, CustomBook> result = new TreeMap<>(comparator);
        result.putAll(booksMap);

        return result;
    }
}
