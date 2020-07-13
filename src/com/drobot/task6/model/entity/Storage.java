package com.drobot.task6.model.entity;

import com.drobot.task6.model.service.IdService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Storage {

    private static final int CAPACITY = 200;

    private static Storage INSTANCE;
    private final Map<UUID, CustomBook> booksMap;

    private Storage() {
        booksMap = new HashMap<>(CAPACITY);
    }

    public static Storage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public Map<UUID, CustomBook> getBooksMap() {
        return Collections.unmodifiableMap(booksMap);
    }

    public List<CustomBook> getBooksList() {
        return new ArrayList<>(booksMap.values());
    }

    public boolean addBook(CustomBook book) {
        boolean result;

        if (booksMap.size() == CAPACITY) {
            result = false;
        } else {
            IdService idService = new IdService();
            UUID id = idService.generateId();
            book.setId(id);
            booksMap.put(id, book);
            result = true;
        }

        return result;
    }

    public boolean removeBook(UUID id) {
        booksMap.remove(id);
        return true;
    }

    public int size() {
        return booksMap.size();
    }

    public boolean containsValue(CustomBook book) {
        return booksMap.containsValue(book);
    }

    public boolean containsKey(UUID id) {
        return booksMap.containsKey(id);
    }
}
