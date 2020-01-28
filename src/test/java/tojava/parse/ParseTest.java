package tojava.parse;

import org.junit.jupiter.api.Test;
import tojava.parse.Parse;
import tojava.test.Task;
import tojava.test.TaskList;
import tojava.test.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {
    @Test
    public void decodeTest_bye() {
        ArrayList<Task> arrList = new ArrayList<>();
        arrList.add(new Todo(""));
        TaskList tasks = new TaskList(arrList);
        assertEquals(true, new Parse("bye").decode(tasks));
        assertEquals(false, new Parse("list").decode(tasks));
    }
}
