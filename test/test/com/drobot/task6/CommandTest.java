package test.com.drobot.task6;

import com.drobot.task6.controller.Invoker;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.model.entity.CustomBook;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.testng.Assert.*;

public class CommandTest {

    @Test(expectedExceptions = CommandException.class)
    public void addBookTest_InvalidParamsLength() throws CommandException {
        Invoker controller = new Invoker();
        controller.processRequest("add_book", "Book 8", "2020", "Vlad");
    }

    @Test
    public void addBookTest_InvalidParams() {
        Optional<Map<UUID, CustomBook>> result = Optional.empty();
        Invoker controller = new Invoker();
        try {
            result = controller.processRequest("add_book", "Book", "sad", "bad", "dad", "Vlad");
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result.isEmpty());
    }

    @Test(expectedExceptions = CommandException.class)
    public void findBookTest_InvalidTag() throws CommandException {
        Invoker controller = new Invoker();
        controller.processRequest("find_book", "release");
    }

    @Test(expectedExceptions = CommandException.class)
    public void findBookTest_InvalidParamsLength() throws CommandException {
        Invoker controller = new Invoker();
        controller.processRequest("find_book", "id", "2020", "Vlad");
    }

    @Test
    public void findBookTest_InvalidId() {
        Optional<Map<UUID, CustomBook>> result = Optional.empty();
        Invoker controller = new Invoker();
        try {
            result = controller.processRequest("find_book", "id", "123");
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result.isEmpty());
    }

    @Test
    public void findBookTest_InvalidIntParameter() {
        Optional<Map<UUID, CustomBook>> result = Optional.empty();
        Invoker controller = new Invoker();
        try {
            result = controller.processRequest("find_book", "pages", "hundred");
        } catch (CommandException e) {
            fail();
        }
        assertTrue(result.isEmpty());
    }
}
