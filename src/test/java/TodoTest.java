import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo test = new Todo("Test AR", false);
        assertEquals("[T][✗] Test AR", test.toString());
    }

    @Test
    public void testWriteFormatConversion() {
        Todo test = new Todo("Test AR", false);
        assertEquals("T|Test AR|0", test.writeFormat());
    }

    @Test
    public void markCompleted_incompleteTodo_successfulChangeInCompleted() {
        Todo test = new Todo("Test AR", false);
        test.makeCompleted();
        assertEquals("T|Test AR|1", test.writeFormat());
    }

    @Test
    public void markCompleted_completeTodo_noChange() {
        Todo test = new Todo("Test AR", true);
        test.makeCompleted();
        assertEquals(test.writeFormat(), test.writeFormat());
    }
}
