package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code Command} enum.
 */
public class CommandTest {

    /**
     * Tests the integrity of the {@code Command} enum.
     */
    @Test
    public void testCommand() {
        try {
            Command.valueOf("TODO");
            Command.valueOf("EVENT");
            Command.valueOf("DEADLINE");
            Command.valueOf("LIST");
            Command.valueOf("DONE");
            Command.valueOf("FIND");
            Command.valueOf("DELETE");
            Command.valueOf("SNOOZE");
            Command.valueOf("SORT");
            Command.valueOf("HELP");
            Command.valueOf("UNDO");
            Command.valueOf("ARCHIVE");
            Command.valueOf("BYE");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Tests the command array of Commands.
     */
    @Test
    public void testCommandArray() {
        for (Command command : Command.values()) {
            assertTrue(command.commands.size() > 0);
        }
    }
}
