package tasks;

import com.sun.tools.javac.comp.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void todosTest() {
        tasks.Task t = new Todo("assignment");
        String expected = "[T][\u2718] assignment";
        assertEquals(expected, t.toString());
    }
}
