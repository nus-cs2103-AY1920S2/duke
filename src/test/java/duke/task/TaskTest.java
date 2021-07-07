package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void setTestDeadlineDone() {
        assertEquals("[D][V] Read book (by: 1 Feb 2020)",
                testMarkAsDone(new Deadline("Read book", LocalDate.parse("2020-02-01"))).toString());
    }

    @Test
    public void setTestEventDone() {
        assertEquals("[E][V] Read book (at: 1 Feb 2020)",
                testMarkAsDone(new Event("Read book", LocalDate.parse("2020-02-01"))).toString());
    }

    @Test
    public void setTestTodoDone() {
        assertEquals("[T][V] Play computer",
                testMarkAsDone(new Todo("Play computer")).toString());
    }

    public Task testMarkAsDone(Task t) {
        t.markAsDone();
        return t;
    }
}
