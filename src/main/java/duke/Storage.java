package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class to store list of tasks.
 */
public class Storage {
    private String filePath;
    private File file;

    Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Save list of tasks to hard disk.
     *
     * @param tasks list of tasks
     */
    protected void save(ArrayList<Task> tasks) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ce) {
                System.out.println("Unable to create file");
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath, StandardCharsets.UTF_8);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write((i + 1) + ". " + tasks.get(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException re) {
            System.out.println("File not found");
        }
    }
}
