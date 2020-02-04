package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        Deadline deadline = new Deadline("JUnit", LocalDate.parse("2020-01-29"));
        assertEquals("[D][✘] JUnit (by: Jan 29 2020)", deadline.toString());
    }
}
