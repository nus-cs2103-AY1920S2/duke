package lcduke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void TestGetDone(){
        Task task1 = new Todo("todo read book");
        assertEquals(false, task1.getDone());
    }
}
