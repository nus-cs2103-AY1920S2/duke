//package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        Task task = new Task("return book");
        assertEquals("[\u2718] return book", task.toString());
    }
    @Test
    public void testGetInstrution() {
        Task task = new Task("return book");
        assertEquals("return book", task.getInstruction());
    }
}