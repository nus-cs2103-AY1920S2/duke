package lcduke;

import lcduke.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetDoneOutput(){
        Task task1 = new Todo("todo read book");
        assertEquals(false, task1.getDone());
    }

    @Test
    public void testToStringOutput(){
        Task task1 = new Todo("todo read book");
        task1.markAsDone();
        assertEquals("[" + "\u2713" + "] " + "todo read book",
                task1.toString());
    }
}
