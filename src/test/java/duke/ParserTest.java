package duke;

import duke.enums.Command;
import duke.exception.BadDateException;
import duke.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void dateParser_success() throws BadDateException {
        assertEquals(LocalDate.parse("2020-10-10"),
                Parser.dateParser("10 10 20"));
    }

    @Test
    public void dateParser_exceptionThrown() {
        try {
            assertEquals(LocalDate.parse("2020-10-10"),
                    Parser.dateParser("2020-10-10"));
            fail();
        } catch (BadDateException e) {
            assertEquals("Bad date format", e.getMessage());
        }
    }

    @Test
    public void commandParser_success() throws InvalidCommandException {
        assertEquals(Command.TODO, Parser.commandParser("todo"));
    }

    @Test
    public void commandParser_exceptionThrown() {
        try {
            assertEquals(Command.TODO, Parser.commandParser("td"));
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("td is an invalid command", e.getMessage());
        }
    }
}
