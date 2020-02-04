import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DukeTest class is a test class for Duke class.
 */
public class DukeTest {

    /**
     * Tests the deadline's toString method for the valid format.
     */
    @Test
    public void testDeadline() {
        LocalDate date = LocalDate.parse("2020-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task deadLine = new Deadline("return book", date, "1800");
        String expectedFormat = "[D][X] return book (by: Feb 2 2020 1800)";
        assertEquals(expectedFormat, deadLine.toString());
    }

    /**
     * Tests the todo's toString method for the valid format.
     */
    @Test
    public void testTodo() {
        Task toDo = new ToDo("read book");
        String expectedFormat = "[T][X] read book";
        assertEquals(expectedFormat, toDo.toString());
    }

    /**
     * Tests the event's toString method for the valid format.
     */
    @Test
    public void testEvent() {
        LocalDate date = LocalDate.parse("2020-02-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task event = new Event("project meeting", date, "2-4pm");
        String expectedFormat = "[E][X] project meeting (at: Feb 5 2020 2-4pm)";
        assertEquals(expectedFormat, event.toString());
    }

}