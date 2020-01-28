import duke.task.Task;
import  duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * The ToDoTest program tests if ToDo task has the
 * correct string representation.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class ToDoTest {

    /**
     * Tests if ToDo task has the correct string representation.
     */
    @Test
        public void test() {

        Task todo = new ToDo(null, "borrow book");
        String str = todo.toString();
        assertEquals("[ToDo][N] borrow book", str);
    }

}
