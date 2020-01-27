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

public class ParserTest {
    @Test
    public void invalidOutput_shouldThrowDukeException() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("Todo borrow book");
        });

        String expectedMessage = "Are you sure you are giving the correct command?";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void inputList_shouldReturnListCommandObject() throws DukeException {
        List<String> expected = new ArrayList<>();
        expected.add(CommandType.LIST.getCommand());
        Assertions.assertEquals(expected, Parser.processUserInput("list"));
    }

    @Test
    public void todoBorrowBookInput_shouldReturnArrayListSuccessfully() throws DukeException {
        List<String> expected = new ArrayList<>();
        expected.add(CommandType.TODO.getCommand());
        expected.add("borrow book");
        Assertions.assertEquals(expected, Parser.addTask("todo borrow book"));
    }
}
