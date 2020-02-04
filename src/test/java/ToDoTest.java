import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dummyTest() throws IOException {
        ToDo test1 = new ToDo("Run", false);

        assertEquals("[T][✘] Run", test1.toString());
        assertEquals("T | 0 | Run", test1.convert());
    }
}