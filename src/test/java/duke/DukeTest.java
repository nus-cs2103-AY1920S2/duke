package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testTodo() {
        Todo td = new Todo("todo jakcnkaj");
        assertEquals("[T][✘] jakcnkaj ", td.toString());
    }

    @Test
    public void testTaskList() {
        Todo td = new Todo("todo jakcnkaj");
        TaskList list = new TaskList();
        list.add(td);
        assertEquals(1 , list.getLength());
    }

    @Test
    public void testTaskListDelete() {
        Todo td = new Todo("todo jakcnkaj");
        Todo tdTwo = new Todo("todo jnkaj");
        TaskList list = new TaskList();
        list.add(td);
        list.add(tdTwo);
        list.delete(1);
        assertEquals(1 , list.getLength());
    }


}
