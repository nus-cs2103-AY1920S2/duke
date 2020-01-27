import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private TaskReader reader;
    private TaskWriter writer;

    public Storage(String filePath) {
        this.reader = new TaskReader(filePath);
        this.writer = new TaskWriter(filePath);
    }

    public ArrayList<Task> loadTasks() throws DukeInvalidDateFormatException, DukeInvalidTaskFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = reader.loadTasks();
        } catch (IOException e) {
            System.err.println(e);
        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            throw e;
        }
        return tasks;
    }

    public void writeTask(Task task, boolean isApppendMode) {
        try {
            this.writer.writeTask(task, isApppendMode);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Rewrites the list of tasks to the file.
     */

    public void rewriteTasksToFile(ArrayList<Task> tasks) {
        this.writer.setBlank();
        try {
            for (int i = 0; i < tasks.size(); i++) {
                this.writer.writeTask(tasks.get(i), i != 0);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
