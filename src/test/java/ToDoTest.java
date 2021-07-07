import org.junit.jupiter.api.Test;
import duke.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void getTaskIcon() {
        assertEquals("T", new ToDo("").getTaskIcon());
    }
}