import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][O] Todo Testing", new Todo("Todo Testing", true).toString());
    }
}