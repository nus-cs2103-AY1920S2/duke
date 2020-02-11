package storage;

import exception.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import task.Task;

public class Storage {
    private Path filePath;
    private File file;

    public Storage(Path filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath.toString());
    }

    /** @param tasks all existing tasks */
    public void update(ArrayList<Task> tasks) {
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (Task t : tasks) {
                fr.write(String.format("%s%n", t.toStorable())); // | separated data
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** @return ArrayList<Task> returns ArrayList of Task based on txt storage */
    public ArrayList<Task> getTasksFromStorage() {
        ArrayList<Task> tempTasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            for (String entry : lines) {
                try {
                    Task newTask = Task.newTaskFromMemory(entry);
                    tempTasks.add(newTask);
                } catch (DukeException err) {
                    System.out.println("File has been corrupted");
                    this.update(tempTasks);
                    return tempTasks;
                }
            }
        } catch (NoSuchFileException e) {
            File directory = new File(Paths.get("storage").toString());
            if (!directory.exists()) {
                directory.mkdir();
            }
            return tempTasks;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempTasks;
    }
}
