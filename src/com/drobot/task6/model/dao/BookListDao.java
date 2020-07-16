package com.drobot.task6.model.dao;

import com.drobot.task6.exception.DaoException;
import com.drobot.task6.model.entity.CustomBook;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookListDao {

    boolean add(CustomBook book) throws DaoException;
    boolean remove(UUID id) throws DaoException;
    Optional<CustomBook> findById(UUID id);
    Map<UUID, CustomBook> findByName(String name);
    Map<UUID, CustomBook> findByReleaseYear(int releaseYear);
    Map<UUID, CustomBook> findByPages(int pages);
    Map<UUID, CustomBook> findByAuthors(List<String> authors);
    Map<UUID, CustomBook> sortByTag(String tag) throws DaoException;
}
