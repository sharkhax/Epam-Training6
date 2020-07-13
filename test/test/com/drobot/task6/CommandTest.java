package test.com.drobot.task6;

import com.drobot.task6.controller.Invoker;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandTest {

    @Test(expectedExceptions = ValueException.class)
    public void addBookTest_InvalidParams() throws ValueException {
        Invoker controller = new Invoker();
        try {
            controller.processRequest("add_book", "Book 8", "2020", "Vlad");
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test(expectedExceptions = ValueException.class)
    public void addBookTest_ParseError() throws ValueException {
        Invoker controller = new Invoker();
        try {
            controller.processRequest("add_book", "Book 8", "13", "202aaaa0", "Vlad");
        } catch (ServiceException e) {
            fail();
        }
    }
}
