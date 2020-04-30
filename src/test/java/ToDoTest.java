import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * ToDoTest class is a test class for ToDo class.
 */
public class ToDoTest {

    /**
     * Tests the todo's toString method for the valid format.
     */
    @Test
    public void testTodoToString() {
        Task toDo = new ToDo("read book");
        String expectedFormat = "[T][X] read book";
        assertEquals(expectedFormat, toDo.toString());
    }

}
