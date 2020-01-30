import dukeClasses.Deadline;
import dukeClasses.Task;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    @Order(1)
    public void testTaskName() {
        Deadline deadlineTest = new Deadline("test", LocalDate.parse("2020-01-01"));
        assertEquals("test", deadlineTest.getDescription());
    }

    @Test
    @Order(2)
    public void testDoneStatus() {
        Deadline deadlineTest = new Deadline("test", LocalDate.parse("2020-01-01"));
        assertEquals(false, deadlineTest.getDescription());
    }



}

