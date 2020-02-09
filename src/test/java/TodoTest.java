import duke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    String description = "desc";
    Todo todo = new Todo(description);

    @Test
    void createdIncompleteTest() {
        assertEquals(todo.isDone, false, "duke.Todo is initialized to incomplete");
    }

    @Test
    void toDatabaseString() {
        assertEquals(todo.toDatabaseString(), "T|0|" + description + "\n",
                "testing encoding of default todo task");
    }
}