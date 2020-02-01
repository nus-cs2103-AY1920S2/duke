package app.core;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import app.core.tasks.Task;

public class StorageManager {
    public static final String STORAGE_DIR = "./data/";
    public static final String STORAGE_FILENAME = "tasks.txt";
    public static final String STORAGE_FILEPATH = STORAGE_DIR + STORAGE_FILENAME;

    public boolean save(List<Task> tasks) {
        try {
            File outputDir = new File(STORAGE_DIR);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            FileWriter writer = new FileWriter(STORAGE_FILEPATH);
            for (Task task : tasks) {
                writer.write(task.toStorage());
                writer.write("\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<Task> load() {
        try {
            List<Task> output = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(STORAGE_FILEPATH));
            reader.lines()
                .forEach(line -> output.add(Task.fromStorage(line)));
            reader.close();
            return output;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}