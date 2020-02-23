import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void todoTest() {
        Task task = new Todo("hi");
        assertEquals("[T][✗] hi", task.toString());
    }

    @Test
    public void storeTest() {
        File file = new File("D:/duke/data/d.txt");
        Store store = new Store(file);
        assertEquals("Now you have 1 tasks in the list.\n"
                        + "____________________________________________________________\n",
                store.todo("hw"));
    }
}
