import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void complete() {
        Todo todo = new Todo("name", false);
        assertEquals(true, todo.complete().getIsDone());
    }
}
