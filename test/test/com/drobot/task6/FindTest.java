package test.com.drobot.task6;

import com.drobot.task6.controller.Invoker;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.exception.DaoException;
import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.dao.impl.BookListDaoImpl;
import com.drobot.task6.model.entity.CustomBook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.testng.Assert.*;

public class FindTest {

    CustomBook book1;
    CustomBook book2;
    CustomBook book3;
    CustomBook book4;
    CustomBook book5;
    CustomBook book6;
    CustomBook book7;

    @BeforeTest
    public void createStorage() throws DaoException {
        BookListDao dao = new BookListDaoImpl();
        List<String> authors1 = new ArrayList<>();
        authors1.add("Petya");
        authors1.add("Vasya");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Ivanov");
        List<String> authors3 = new ArrayList<>();
        authors3.add("Petya");
        authors3.add("Ivanov");
        List<String> authors4 = new ArrayList<>();
        authors4.add("Sasha");
        authors4.add("Pasha");
        List<String> authors5 = new ArrayList<>();
        authors5.add("Ivan");
        List<String> authors6 = new ArrayList<>();
        authors6.add("Narod");
        List<String> authors7 = new ArrayList<>();
        authors7.add("Vasya");
        authors7.add("Petya");
        book1 = new CustomBook("Book 1", 2000, 120, authors1);
        book2 = new CustomBook("Book 2", 2010, 12, authors2);
        book3 = new CustomBook("Book 3", 2001, 10, authors3);
        book4 = new CustomBook("Book 6", 1871, 20, authors4);
        book5 = new CustomBook("Book 5", 1920, 30, authors5);
        book6 = new CustomBook("Book 6", 2002, 20, authors6);
        book7 = new CustomBook("Book 6", 2010, 435, authors7);
        dao.add(book1);
        dao.add(book2);
        dao.add(book3);
        dao.add(book4);
        dao.add(book5);
        dao.add(book6);
        dao.add(book7);
    }

    @Test
    public void findByNameTest_OneBook() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "name", "Book 5");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                if (booksList.size() == 1) {
                    result = booksList.get(0).equals(book5);
                }
            }
        } catch (CommandException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByNameTest_SomeBooks() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "name", "Book 6");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                for (CustomBook book : booksList) {
                    if (book.equals(book4) || book.equals(book6) || book.equals(book7)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByNameTest_False() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "name", "Book");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                if (booksList.size() == 1) {
                    result = booksList.get(0).equals(book2);
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertFalse(result);
    }

    @Test
    public void findByIdTest_True() {
        Invoker invoker = new Invoker();
        UUID id = book3.getBookId();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "id", id.toString());
            if (optional.isPresent()) {
                booksMap = optional.get();
                if (booksMap.get(id).equals(book3)) {
                    result = true;
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByIdTest_False() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "id", UUID.randomUUID().toString());
            if (optional.isEmpty()) {
                result = true;
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByReleaseYearTest_True() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "release_year", "2010");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                for (CustomBook book : booksList) {
                    if (book.equals(book2) || book.equals(book7)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByReleaseYearTest_False() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "release_year", "1810");

            if (optional.isEmpty()) {
                result = true;
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByPagesTest_True() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "pages", "20");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                for (CustomBook book : booksList) {
                    if (book.equals(book4) || book.equals(book6)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByPagesTest_False() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "pages", "21");

            if (optional.isEmpty()) {
                result = true;
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByAuthors_OneAuthor() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "authors", "Petya");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                for (CustomBook book : booksList) {
                    if (book.equals(book1) || book.equals(book3) || book.equals(book7)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByAuthors_SomeAuthors() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        List<CustomBook> booksList;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "authors", "Petya", "Vasya");
            if (optional.isPresent()) {
                booksMap = optional.get();
                booksList = new ArrayList<>(booksMap.values());
                for (CustomBook book : booksList) {
                    if (book.equals(book1) || book.equals(book7)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test
    public void findByAuthors_False() {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        boolean result = false;
        try {
            optional = invoker.processRequest("find_book", "authors", "Peya");
            if (optional.isEmpty()) {
                result = true;
            }
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result);
    }
}
