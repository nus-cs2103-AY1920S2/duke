package dukeapp.parse;

import org.junit.jupiter.api.Test;
import dukeapp.files.*;

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
