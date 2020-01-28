import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.LinkedList;
import java.nio.file.Paths;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class DataManager {
    Path path;

    public DataManager() {
        String currentDirectory = System.getProperty("user.dir");
        Path dataDirectory = java.nio.file.Paths.get(currentDirectory, "data");
        this.path = java.nio.file.Paths.get(currentDirectory, "data", "duke.txt");

        if (!java.nio.file.Files.exists(dataDirectory)) {
            try {
                java.nio.file.Files.createDirectory(dataDirectory);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void saveData(LinkedList<Task> list) {
        try {
            java.nio.file.Files.deleteIfExists(path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String taskString = task.toString() + "\n";
            byte data[] = taskString.getBytes();

            try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(this.path, CREATE, APPEND))) {
                out.write(data, 0, data.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void loadData(Tracker tracker) {

    }

    public boolean hasPreviousData() {
        return java.nio.file.Files.exists(this.path);
    }
}
