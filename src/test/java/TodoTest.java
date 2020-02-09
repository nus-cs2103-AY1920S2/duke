import duke.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void test() {
        Todo todo = new Todo("Read 10 Books");
        String result = todo.toString();
        assertEquals("[T][✘] Read 10 Books", result);
    }

}
