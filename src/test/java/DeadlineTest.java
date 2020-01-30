import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getMnemonic() {
        Task task = new Deadline("", null, false);
        assertEquals("D", task.getMnemonic());
    }

    @Test
    public void getName() {
        Task task = new Deadline("abc", null, false);
        assertEquals("abc", task.getName());
    }
}
