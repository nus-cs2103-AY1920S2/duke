package lcduke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetDoneOutput() {
        lcduke.Task task1 = new lcduke.Todo("todo testing todo function");
        assertEquals(false, task1.getDone());
    }

    @Test
    public void testToString() {
        lcduke.Task task1 = new lcduke.Todo("todo read book");
        task1.markAsDone();
        assertEquals("[T][" + Character.toString((char)43) + "] " + "todo read book",
                task1.toString());
    }

    @Test
    void getStatusIcon() {
    }

    @Test
    void markAsDone() {
    }

    @Test
    void getDone() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void printInit() {
    }
}
