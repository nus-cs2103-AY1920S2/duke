package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    public void runGetCommand_ShouldReturnCorrectCommandType() {
        Command command = new Command(CommandType.DELETE);
        Assertions.assertEquals(command.getCommandType(), CommandType.DELETE);
    }

    @Test
    public void nonExitCommand_ShouldNotExit() {
        Command command = new Command(CommandType.DELETE);
        Assertions.assertFalse(command.isExitLoop());
    }

    @Test
    public void ExitCommand_ShouldExit() {
        Command command = new ExitCommand(CommandType.BYE);
        Assertions.assertTrue(command.isExitLoop());
    }
}
