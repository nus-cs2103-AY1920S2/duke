package parser;

import org.junit.jupiter.api.Test;
import tasks.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void handleTaskCommand_invalidCommand_ExceptionCaught() {
        try {
            new Parser().handleTaskCommand("Hello", new TaskList());
        } catch (Exception e) {
            fail();
        }
    }
}
