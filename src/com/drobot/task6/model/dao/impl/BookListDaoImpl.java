package com.drobot.task6.model.dao.impl;

import com.drobot.task6.exception.DaoException;
import com.drobot.task6.model.comparator.*;
import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.entity.Storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

public class BookListDaoImpl implements BookListDao {

    private final Storage storage = Storage.getInstance();

    @Override
    public boolean add(CustomBook book) throws DaoException {
        if (book == null) {
            throw new DaoException("The book is null");
        }
        if (storage.containsValue(book)) {
            throw new DaoException("The book is already contained");
        }
        return storage.add(book);
    }

    @Override
    public boolean remove(UUID id) throws DaoException {
        if (storage.containsKey(id)) {
            CustomBook book = storage.getBooksMap().get(id);
            return storage.remove(book);
        } else throw new DaoException("There is no such book");
    }

    @Override
    public Optional<CustomBook> findById(UUID id) {
        Optional<CustomBook> result = Optional.empty();
        if (storage.containsKey(id)) {
            Map<UUID, CustomBook> booksMap = storage.getBooksMap();
            CustomBook book = booksMap.get(id);
            result = Optional.of(book);
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> findByName(String name) {
        List<CustomBook> booksList = storage.getBooksList();
        Map<UUID, CustomBook> result = new HashMap<>();
        for (CustomBook book : booksList) {
            if (book.getName().equals(name)) {
                result.put(book.getBookId(), book);
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
                result.put(book.getBookId(), book);
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
                result.put(book.getBookId(), book);
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
                result.put(book.getBookId(), book);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, CustomBook> sortByTag(String tag) throws DaoException {
        BookMapComparatorType comparatorType;
        if (tag.equalsIgnoreCase("id")) {
            return sortById();
        }
        try {
            comparatorType = BookMapComparatorType.valueOf(tag);
        } catch (IllegalArgumentException e) {
            throw new DaoException("No comparator");
        }
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        Map<UUID, CustomBook> result = new LinkedHashMap<>();
        Comparator<Map.Entry<UUID, CustomBook>> comparator = comparatorType.getComparator();
        List<Map.Entry<UUID, CustomBook>> list = new ArrayList<>(booksMap.entrySet());
        list.sort(comparator);
        for (Map.Entry<UUID, CustomBook> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private Map<UUID, CustomBook> sortById() {
        Map<UUID, CustomBook> booksMap = storage.getBooksMap();
        return new TreeMap<>(booksMap);
    }
}
