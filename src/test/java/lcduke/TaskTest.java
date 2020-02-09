package lcduke;

import lcduke.Task;
import lcduke.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetDoneOutput(){
        lcduke.Task task1 = new lcduke.Todo("todo read book");
        assertEquals(false, task1.getDone());
    }

    @Test
    public void testToStringOutput(){
        lcduke.Task task1 = new lcduke.Todo("todo read book");
        task1.markAsDone();
        assertEquals("[" + "\u2713" + "] " + "todo read book",
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

    @Test
    void testToString() {
    }

    @Test
    void testToString1() {
    }
}
