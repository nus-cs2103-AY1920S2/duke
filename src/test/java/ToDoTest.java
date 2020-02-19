import Duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    private ToDo newToDo() {
        return new ToDo("read");
    }

    @Test
    void taskStateString() {
        assertEquals("[✗]", newToDo().taskStateString());
    }

    @Test
    void markDone() {
        assertEquals(true, newToDo().markDone());
    }

    @Test
    void testToString() {
        assertEquals("[T][✗] read", newToDo().toString());
    }
}