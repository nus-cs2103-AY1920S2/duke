package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testProcessCommand() throws EmptyDescriptionException, InvalidTaskInputException,
            InvalidCommandException, TaskIndexOutOfBoundsException {
        String input = "deadline read book /by 2020-02-02";
        String[] inputs = input.split(" ");
        assertEquals(Ui.DEADLINE, new Parser().processCommand(inputs[0], inputs));
    }
}
