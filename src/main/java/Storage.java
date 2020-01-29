import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * Loads and Stores the TaskList to Path.
 */
public class Storage {

    private Path path;

    public Storage(Path path) throws IOException {
        this.path = path;
    }

    /**
     * Loads the tasks from Path into TaskList.
     * @return Updated TaskList.
     * @throws IOException If Path is not accessible.
     */
    public TaskList load() throws IOException {
        List<String> lines = Files.readAllLines(path);
        TaskList tasks = new TaskList();

        for (int i = 0; i < lines.size(); i++) {
            String curLine = lines.get(i);
            String[] components = curLine.split(" , ");

            if (components.length < 3) {
                continue;
            }
            if (components[0].equals("T")) {
                Task t = new ToDo(" " + components[2]);
                t.setStatus(components[1]);
                tasks.addTask(t);
            } else if (components[0].equals("D")) {
                Task t = new Deadline(" " + components[2], LocalDate.parse(components[3]));
                t.setStatus(components[1]);
                tasks.addTask(t);
            } else if (components[0].equals("E")) {
                Task t = new Event(" " + components[2], LocalDate.parse(components[3]));
                t.setStatus(components[1]);
                tasks.addTask(t);
            } else {
                System.err.print("Wrong File Structure");
            }
        }
        return tasks;
    }

    /**
     * Saves the current TaskList into Path.
     * @param tasks Current TaskList.
     * @throws IOException If Path is not accessible.
     */
    public void save(TaskList tasks) throws IOException {
        String savedString = tasks.tasksToString();
        BufferedWriter writer = Files.newBufferedWriter(path);
        writer.write(savedString);
        writer.flush();
    }

}
