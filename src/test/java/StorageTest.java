import org.junit.jupiter.api.Test;
import tasks.TaskList;
import tasks.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    void readSaveFileTest() {
        try {
            String relative = "../../../save_file.txt";
            Storage storage = new Storage(relative);
            TaskList taskList = new TaskList();
            BufferedWriter writer = new BufferedWriter(new FileWriter(relative, false));
            writer.write("D | 1 | presentation | 12:00 2020-01-29\n"
                    + "E | 0 | meeting | - 2020-12-03");
            writer.close();

            storage.readSaveFile(taskList);
            assertEquals("[D][O] presentation (by: 12:00, Jan 29 2020)\n"
                    + "[E][X] meeting (at: Dec 3 2020)\n", taskList.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void saveFileTest() {
        try {
            Storage storage = new Storage();
            TaskList taskList = new TaskList();
            taskList.add(new Todo("eat dinner"));
            taskList.add(new Todo("eat supper"));
            storage.save(taskList);
            BufferedReader reader = new BufferedReader(
                    new FileReader("/Users/ChesterSim/Desktop/NUS Computer Science/CS2103T/duke/save_file.txt"));
            String firstLine = reader.readLine();
            assertEquals("T | 0 | eat dinner", firstLine);
            String secondLine = reader.readLine();
            assertEquals("T | 0 | eat supper", secondLine);
        } catch (IOException ex) {
            System.out.println("IO Exception!!!");
        }

    }
}

