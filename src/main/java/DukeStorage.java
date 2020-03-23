import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles all storage in duke.
 */
public class DukeStorage {
    private String filePath;

    public DukeStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to a .txt file in the same directory
     * @param tasks saves tasks
     */
    public void saveTasks(TaskList tasks) {
        List<Task> allTasks = tasks.getAllTasks();
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task: allTasks) {
                if (task instanceof ToDos) {
                    writer.write("T");
                } else if (task instanceof Deadlines) {
                    writer.write("D");
                } else if (task instanceof Events) {
                    writer.write("E");
                }
                writer.write("/divide" + task.isDone() + "/divide" + task.getDescription());
                if (task instanceof Deadlines) {
                    writer.write("/divide" + ((Deadlines) task).getDeadline());
                } else if (task instanceof Events) {
                    writer.write("/divide" + ((Events) task).getVenue());
                }
                writer.write("\n");
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    /**
     * Reads .txt file from same directory
     * @return
     */
    public TaskList readText() {
        TaskList tasks = new TaskList();
        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(filePath));
            String s;
            while ((s = bufferedreader.readLine()) != null) {
                String[] taskParams = s.split("/divide");
                Task task = Task.createStartingTask(taskParams);
                tasks.add(task);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return tasks;
    }
}
