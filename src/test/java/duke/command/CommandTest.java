package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <h1>CommandTest Class</h1>
 * Test for the command class
 *
 * @author Eng Xuan En
 */
public class CommandTest {

    /**
     * Test if the command type return is correct when call getCommandType method.
     */
    @Test
    public void runGetCommand_shouldReturnCorrectCommandType() {
        Command command = new Command(CommandType.DELETE);
        Assertions.assertEquals(command.getCommandType(), CommandType.DELETE);
    }

    /**
     * Test if the delete command return false when call isExitLoop method.
     */
    @Test
    public void nonExitCommand_shouldNotExit() {
        Command command = new Command(CommandType.DELETE);
        Assertions.assertFalse(command.isExitLoop());
    }

    /**
     * Test if the exit command will return true when call isExitLoop method.
     */
    @Test
    public void ExitCommand_shouldExit() {
        Command command = new ExitCommand(CommandType.BYE);
        Assertions.assertTrue(command.isExitLoop());
    }
}
