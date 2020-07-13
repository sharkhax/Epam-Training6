package test.com.drobot.task6;

import com.drobot.task6.controller.Invoker;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.testng.Assert.*;

public class SortTest {

    List<CustomBook> books;

    @BeforeTest
    public void createStorage() {
        StorageService service = new StorageService();
        List<String> authors1 = new ArrayList<>();
        authors1.add("Petya");
        authors1.add("Vasya");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Ivanov");
        authors2.add("Petya Petrov");
        List<String> authors3 = new ArrayList<>();
        authors3.add("Petya");
        authors3.add("Ivanov");
        List<String> authors4 = new ArrayList<>();
        authors4.add("Sasha");
        authors4.add("Sasha Drugoy");
        List<String> authors5 = new ArrayList<>();
        authors5.add("Ivan");
        List<String> authors6 = new ArrayList<>();
        authors6.add("Narod");
        List<String> authors7 = new ArrayList<>();
        authors7.add("Vasya");
        authors7.add("Petya");
        try {
            service.addBook("Book 1", 2000, 120, authors1);
            service.addBook("Book 2", 2010, 12, authors2);
            service.addBook("Book 3", 2001, 10, authors3);
            service.addBook("Book 4", 1871, 20, authors4);
            service.addBook("Book 5", 1920, 30, authors5);
            service.addBook("Book 6", 2002, 45, authors6);
            service.addBook("Book 7", 2012, 435, authors7);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void sortBooksTest() {
        final String tag = "authors";

        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        Map<UUID, CustomBook> booksMap;
        boolean result = false;

        try {
            optional = invoker.processRequest("sort_books", tag);

            if (optional.isPresent()) {
                booksMap = optional.get();
                books = new ArrayList<>(booksMap.values());
                result = true;
            }
        } catch (ServiceException | ValueException e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(result);
    }

    @AfterTest
    public void showBooks() {
        for (CustomBook book : books) {
            System.out.println(book.toString());
        }
    }
}
