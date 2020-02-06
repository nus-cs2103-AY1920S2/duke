import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void TaskTest(){
        Task T = new Task("hi");
        assertEquals("[✖] hi",T.toString());
    }
    @Test
    public void StoreTest(){
        File file = new File("D:/duke/data/d.txt");
        Store store = new Store(file);
        assertEquals("Now you have 1 tasks in the list.\n____________________________________________________________\n", store.todo("hw"));
    }
}
