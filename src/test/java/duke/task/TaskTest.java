package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void getStatusIcon() {
        Todo task = new Todo("Eat dinner", false);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void getDescription() {
        Event event = new Event("CNY Visiting", LocalDate.now(), false);
        assertEquals("CNY Visiting", event.getDescription());
    }
}