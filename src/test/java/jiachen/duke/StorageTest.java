package jiachen.duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    private String path = "./data/test.txt";

    @Test
    void load() {
        try {
            if (Files.exists(Paths.get(path))) {
                Files.delete(Paths.get(path));
            }
            Files.write(Paths.get(path), (
                    "D | 1 | do ma homework | 01/12/2020 0700\n" +
                            "E | 0 | take a nap? | 01/12/2020 0700\n" +
                            "E | 0 | take a nap | 01/12/2020 0800\n" +
                            "T | 0 | test task\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Storage storage = new Storage(path);
        TaskList tasks = null;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }

        assertEquals(tasks.size(), 4);
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void save() {

        if (Files.exists(Paths.get(path))) {
            try {
                Files.delete(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        TaskList tasks = new TaskList();
        tasks.add(new MockTask("mock task 1"));
        tasks.add(new MockTask("mock task 2"));
        tasks.add(new MockTask("mock task 3"));

        Storage storage = new Storage(path);

        storage.save(tasks);

        assert (Files.exists(Paths.get(path)));
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}