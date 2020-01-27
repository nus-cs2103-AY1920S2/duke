import duke.src.main.java.Task;
import duke.src.main.java.ToDo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DukeTest {

@Test
    public void testToString() {

    Task todo = new Todo(null, "borrow book" );
    String str = todo.toString();

    assertEquals("[duke.Task.ToDo][N] borrow book", str);

}


}