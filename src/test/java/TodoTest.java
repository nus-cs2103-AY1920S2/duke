import org.junit.jupiter.api.Test;
import task.Event;
import task.Todo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        Todo td = new Todo("finish CS2103T");
        assertEquals("[T]\u2718 finish CS2103T", td.toString());
    }

    @Test
    public void isDoneTest() {
        Todo td = new Todo("finish CS2103T");
        td.taskDone();
        assertEquals("[T]\u2713 finish CS2103T", td.toString());
    }
}
