package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getDescriptionTest() {
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-03-20"));
        assertEquals("return book", deadline.getDescription());
    }

    @Test
    public void toStringTest() {
        assertEquals("[D][✘] return book (by: Mar 20 2020)",
                new Deadline("return book", LocalDate.parse("2020-03-20")).toString());
    }
}
