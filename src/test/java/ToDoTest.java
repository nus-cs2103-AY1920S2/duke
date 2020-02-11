
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConvert() {
        assertEquals("[T][O] read book",
                new ToDos("read book", false).toString());
        assertEquals("[T][X] read book",
                new ToDos("read book", true).toString());
    }
}