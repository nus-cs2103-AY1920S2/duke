import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createTaskTest() {
        String desc = "name_test";
        Task sample = new Task(desc);
        assertEquals(sample.getFileString(), "T|false|" + desc);
    }

    @Test
    public void doTaskTest() {
        String desc = "name_test";
        Task sample = new Task(desc);
        assertEquals(sample.done(), "[T][\u2713] " + desc);
    }

}