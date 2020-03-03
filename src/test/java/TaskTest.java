import org.junit.jupiter.api.Test;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void writeToFile(){
        Task test = new Task("read book");
        String testStr = "T / 0 / read book";
        assertEquals(testStr, test.writeToFile());
    }
}