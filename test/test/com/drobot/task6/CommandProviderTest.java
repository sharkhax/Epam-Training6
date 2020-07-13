package test.com.drobot.task6;

import com.drobot.task6.controller.Invoker;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.testng.Assert.*;

public class CommandProviderTest {

    @Test
    public void addBookTest_WrongCommand() {
        Invoker controller = new Invoker();
        boolean result = false;
        Optional<Map<UUID, CustomBook>> optional;
        try {
            optional = controller.processRequest("add", "Book 8", "2020", "241", "Vlad");
            result = optional.isEmpty();
        } catch (ValueException | ServiceException e) {
            fail();
        }
        assertTrue(result);
    }
}
