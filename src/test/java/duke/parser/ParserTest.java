package duke.parser;

import duke.DukeException;
import duke.command.CommandType;
import duke.command.ListCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>ParserTest Class</h1>
 * Test for parser class.
 *
 * @author  Eng Xuan En
 */
public class ParserTest {

    /**
     * Test if invalid input to the parse method will throws DukeException or not.
     */
    @Test
    public void invalidOutput_shouldThrowDukeException() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("Todo borrow book");
        });

        String expectedMessage = "Are you sure you are giving the correct command?";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if giving "list" to processUserInput method will give list command object or not.
     * @throws DukeException occurs when invalid string input
     */
    @Test
    public void inputList_shouldReturnListCommandObject() throws DukeException {
        List<String> expected = new ArrayList<>();
        expected.add(CommandType.LIST.getCommand());
        Assertions.assertEquals(expected, Parser.processUserInput("list"));
    }

    /**
     * Test if giving "todo borrow book" to addTask method will give the ArrayList with the correct data or not.
     * @throws DukeException occurs when invalid string input
     */
    @Test
    public void todoBorrowBookInput_shouldReturnArrayListSuccessfully() throws DukeException {
        List<String> expected = new ArrayList<>();
        expected.add(CommandType.TODO.getCommand());
        expected.add("borrow book");
        Assertions.assertEquals(expected, Parser.addTask("todo borrow book"));
    }
}
