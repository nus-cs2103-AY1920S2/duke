import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoString() {
        String inputString = "sleep";
        String expectedString = "[T][" + "\u2718" + "] sleep";
        assertEquals(expectedString, new ToDo(inputString).toString());

    }
}
