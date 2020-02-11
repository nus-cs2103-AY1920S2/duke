package jiachen.duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class StorageTest {

    private String path = "./data/test.txt";

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
