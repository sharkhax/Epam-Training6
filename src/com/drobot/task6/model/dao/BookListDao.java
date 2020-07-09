package com.drobot.task6.model.dao;

import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.exception.BookException;

import java.util.List;

public interface BookListDao {

    boolean addBook(CustomBook book) throws BookException;
    boolean removeBook(CustomBook book) throws BookException;

    CustomBook findById(long id) throws BookException;
    List<CustomBook> findByName(String name) throws BookException;
    List<CustomBook> findByReleaseYear(int releaseYear) throws BookException;
    List<CustomBook> findByPages(int pages) throws BookException;
    List<CustomBook> findByAuthor(String author) throws BookException;

    List<CustomBook> sortBooksById();
    List<CustomBook> sortBooksByName();
    List<CustomBook> sortBooksByReleaseYear();
    List<CustomBook> sortBooksByPages();
    List<CustomBook> sortBooksByAuthor();

    boolean contains(CustomBook book);
}
