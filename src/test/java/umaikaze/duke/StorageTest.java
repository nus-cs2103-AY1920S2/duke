package umaikaze.duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import umaikaze.duke.task.Task;
import umaikaze.duke.task.Todo;

public class StorageTest {
    @Test
    public void saveFile_normalInput_Todo() throws IOException, DukeException {
        String dir = "data.txt";
        List<Task> list = new ArrayList<>();
        list.add(new Todo("TestTodo"));
        Storage storage = new Storage(dir);
        storage.saveFile(list);
        BufferedReader br = new BufferedReader(new FileReader(dir));
        assertEquals("T|0|TestTodo", br.readLine());
    }
    @Test
    public void loadFile_normalInput_Todo() throws IOException, DukeException {
        String dir = "data.txt";
        List<Task> list;
        Storage storage = new Storage(dir);
        list = storage.loadFile();
        assertEquals("[T][\u2718] TestTodo", list.get(0).toString());
    }
}
