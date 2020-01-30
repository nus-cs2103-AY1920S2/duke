import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testWriteFile() {
        Storage storage = new Storage("data/duke.txt");
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("read book");
        taskList.arr.add(new Todo("read book"));
        storage.writeFile(taskList);
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/duke.txt"));
            assertEquals("T/0/read book", br.readLine());
        } catch (Exception ignored) {
        }
    }
}
