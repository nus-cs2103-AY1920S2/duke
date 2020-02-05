package dukeApp.action;

import dukeApp.files.Task;
import dukeApp.files.Todo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    @Test
    public void checkNumTest() {
        ArrayList<Task> arrList = new ArrayList<Task>();
        arrList.add(new Todo(""));
        assertEquals(false, new Action(2, arrList).checkNum());
    }
}
