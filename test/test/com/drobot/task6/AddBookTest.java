package test.com.drobot.task6;

import com.drobot.task6.controller.Invoker;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.entity.Storage;
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

public class AddBookTest {

    @BeforeTest
    public void createStorage() {
        StorageService service = new StorageService();
        List<String> authors1 = new ArrayList<>();
        authors1.add("Petya");
        authors1.add("Vasya");
        List<String> authors2 = new ArrayList<>();
        authors2.add("Ivanov");
        authors2.add("Petya Petrov");
        try {
            service.addBook("Book 1", 2000, 120, authors1);
            service.addBook("Book 2", 2010, 12, authors2);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void addBookTest_True() {
        Invoker invoker = new Invoker();
        boolean result = false;
        Optional<Map<UUID, CustomBook>> optional;
        try {
            optional = invoker.processRequest("add_book", "Book 8", "1820", "241", "Vlad", "Drobot", "Sergey");
            result = optional.isPresent();
        } catch (ValueException | ServiceException e) {
            fail();
        }
        assertTrue(result);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void addBookTest_BookExists() throws ServiceException {
        Invoker invoker = new Invoker();
        try {
            invoker.processRequest("add_book", "Book 8", "2010", "241", "Vlad");
            invoker.processRequest("add_book", "Book 8", "2010", "241", "Vlad");
        } catch (ValueException e) {
            fail();
        }
    }

    @AfterTest
    public void showBooks() {
        Storage storage = Storage.getInstance();
        List<CustomBook> books = storage.getBooksList();
        for (CustomBook book : books) {
            System.out.println(book.toString());
        }
    }
}
