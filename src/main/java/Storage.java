import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * A Storage object contains a <code>filePath</code>, the location that can be used to find the file storing the task
 * list or create a new file at.
 */
public class Storage {
    protected File file;
    protected TaskList tasks;
    protected ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.tasks = new TaskList();
        this.taskList = tasks.getList();
    }

    /**
     * Returns task list stored in file on hard disk or throw an exception and create a new file to store task list.
     *
     * @return task list saved in hard disk.
     * @throws DukeException if file is not found and cannot be loaded.
     * @throws IOException if there is error reading data from a file.
     */
    public ArrayList<Task> load() throws DukeException, IOException {
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                Parser HDParser = new Parser(tasks);
                HDParser.loadText(line);
                line = br.readLine();
            }
            return taskList;
        } else {
            boolean fileCreated = file.createNewFile();
            throw new DukeException("LOAD_ERROR");
        }
    }

    public void updateTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Updates the file stored in hard disk with all the tasks that are still present in the task list at the point when
     * the user decides to exit the application.
     *
     * @throws IOException if there is error in writing to the file.
     */
    public void updateHD() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Task task: taskList) {
            writer.write(task.updateFile() + "\n");
        }
        writer.flush();
    }
}
