import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class TodoTest {
    @Test
    public void todoTest() {
        assertEquals(new Todo("pass this case").toString(), "[T][\u2718] pass this case");
    }
}
