import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private Path file;

    public Storage(Path file) {
        this.file = file;
    }

    private void openFile() throws IOException {
        if (!Files.exists(file)) {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        }
    }

    /**
     * Load tasks from given file.
     * @return an array of task that store in the file
     * @throws IOException if file is unreadable
     */

    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        TaskFactory taskFactory = new TaskFactory();

        openFile();
        BufferedReader reader = Files.newBufferedReader(file);
        int numberOfTasks = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= numberOfTasks; i++) {
            tasks.add(taskFactory.readTask(reader));
        }
        reader.close();
        return tasks;
    }

    /**
     * Store an array of tasks to the file.
     * @param tasks the array of tasks
     * @throws IOException if can't write to the file
     */

    public void storeFile(ArrayList<Task> tasks) throws IOException {
        openFile();
        BufferedWriter writer = Files.newBufferedWriter(file);
        writer.write(String.valueOf(tasks.size()));
        writer.newLine();
        for (Task task : tasks) {
            writer.write(task.getString());
            writer.newLine();
        }
        writer.close();
    }
}
