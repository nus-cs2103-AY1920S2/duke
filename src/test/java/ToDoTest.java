import duke.Task.Task;
import  duke.Task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;



public class ToDoTest {

        @Test
        public void test() {

            Task todo = new ToDo(null, "borrow book" );
            String str = todo.toString();

            assertEquals("[ToDo][N] borrow book", str);
        }


}
