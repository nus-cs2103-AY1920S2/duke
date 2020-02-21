package duke;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void readFileException() {
        Storage storage = new Storage("../../data/test.txt");
        try {
            ArrayList<Task> tasks = storage.printFileContents();
        } catch (FileNotFoundException e) {
            assertEquals("../../data/test.txt (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void readFile() {
        Storage storage = new Storage("../../data/duke.txt");
        try {
            ArrayList<Task> tasks = storage.printFileContents();
        } catch (FileNotFoundException e) {
            assertEquals("../../data/duke.txt (No such file or directory)", e.getMessage());
        }
    }

    @Test void writeFile() {
        Storage storage = new Storage("../../data/duke.txt");
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> content = new ArrayList<Task>();
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("return book", LocalDate.parse("2020-10-10")));
        tasks.add(new Event("have dinner", LocalDate.parse("2020-12-12")));
        tasks.add(new Event("party", LocalDate.parse("2020-12-20")));
        storage.writeToFile(tasks);

        try {
            content = storage.printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        String str = "";
        for (Task task : content) {
            str += task.description + " ";
        }
        assertEquals("read book return book have dinner party ", str);
    }
}
