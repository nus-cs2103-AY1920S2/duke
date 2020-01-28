package dukeapp.action;

import org.junit.jupiter.api.Test;
import dukeapp.files.Task;
import dukeapp.files.Todo;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    @Test
    public void checkNumTest() {
        ArrayList<Task> arrList = new ArrayList<Task>();
        arrList.add(new Todo(""));
        assertEquals(0, new Action(2, arrList).checkNum());
    }
}
