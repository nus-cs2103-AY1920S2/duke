package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_wrongCommand_exceptionThrown(){
        //wrong delete command
        try {
            Parser.parse("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of delete cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("delete abc");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of delete have to be a number.", e.getMessage());
        }

        //wrong done command
        try {
            Parser.parse("done  ");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of done cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("done abc");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of done have to be a number", e.getMessage());
        }

        //wrong add command
        try {
            Parser.parse("todo ");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a task cannot be empty.", e.getMessage());
        }

        //invalid command
        try {
            Parser.parse("help");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
