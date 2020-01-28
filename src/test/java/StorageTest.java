import org.junit.jupiter.api.Test;
import tasks.TaskList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void saveTest() {
       try {
           Storage storage = new Storage();
           TaskList taskList = new TaskList();
           BufferedWriter writer = new BufferedWriter(new FileWriter("save_file.txt", false));
           writer.write("D | 1 | presentation | 12:00 2020-01-29\n");
           storage.save(taskList);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}