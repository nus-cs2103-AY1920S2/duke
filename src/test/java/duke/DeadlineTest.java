package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void markAsDoneTest(){
        Deadline deadline = new Deadline("return book", LocalDate.parse("2020-03-20"));
        deadline.markAsDone();
        assertEquals(true, deadline.isTaskDone());
    }

    @Test
    public void toStringTest(){
        assertEquals("[D][\u2718] return book (by: Mar 20 2020)",
                new Deadline("return book", LocalDate.parse("2020-03-20")).toString());
    }
}
