import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Todo;

public class TodoTest {

    @Test
    public void todostringTest() {
        Todo todo = new Todo("Mark papers");
        assertEquals("[T] [X] Mark papers",todo.toString());
    }
}