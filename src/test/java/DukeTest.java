import org.junit.jupiter.api.Test;

import model.Task;
import model.ToDoTask;
import exceptions.NoDescriptionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DukeTest {

    @Test
    public void noDescription_ToDoTask_ExceptionThrown() {
        try {
            Task newTask = new ToDoTask("");
            fail();
        } catch (NoDescriptionException e) {
            assertEquals(e.getMessage(),
                    "OOPS!!! The description of a todo task cannot be empty.\n"
            );
        }
    }
}
