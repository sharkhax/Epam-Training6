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

public class RemoveBookTest {

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

        try {
            service.addBook("Book 1", 2000, 120, authors1);
            service.addBook("Book 2", 2010, 12, authors2);
            service.addBook("Book 3", 2001, 10, authors3);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void removeBookTest_True() {
        Invoker invoker = new Invoker();
        Storage storage = Storage.getInstance();
        UUID id = storage.getBooksList().get(0).getId();
        Optional<Map<UUID, CustomBook>> optional;
        boolean result = false;

        try {
            optional = invoker.processRequest("remove_book", id.toString());
            result = optional.isPresent();

        } catch (ValueException | ServiceException e) {
            fail();
        }

        assertTrue(result);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void removeBookTest_NotContained() throws ServiceException {
        Invoker invoker = new Invoker();
        Optional<Map<UUID, CustomBook>> optional;
        boolean result = false;

        try {
            optional = invoker.processRequest("remove_book", UUID.randomUUID().toString());
            result = optional.isPresent();

        } catch (ValueException e) {
            fail();
        }

        assertTrue(result);
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
