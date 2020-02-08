import org.junit.jupiter.api.Test;

import main.java.model.Task;
import main.java.model.ToDoTask;
import main.java.exceptions.NoDescriptionException;

public class DukeTest {

    @Test
    public void noDescription_ToDoTask_ExceptionThrown() {
        try{
            Task newTask = new ToDoTask("");
        } catch (NoDescriptionException e) {
            assertEquals(e.getMessage(),
                    )
        }
    }
}
