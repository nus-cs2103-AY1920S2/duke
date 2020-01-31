import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void testCreateTodo() {
        assertEquals("[T][N] borrow book", new Todo("borrow book").toString());
    }
}

