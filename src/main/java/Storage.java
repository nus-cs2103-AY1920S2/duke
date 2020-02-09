import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Storage object contains a <code>filePath</code>, the location that can be used to find the file storing the task
 * list or create a new file at.
 */
public class Storage {
    protected File file;
    protected ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.taskList = new ArrayList<>();
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
                Parser dummyParser = new Parser(taskList);
                dummyParser.loadText(line);
                line = br.readLine();
            }
            return taskList;
        } else {
            boolean isFileCreated = file.createNewFile();
            throw new DukeException("LOAD_ERROR");
        }
    }

    public void updateHD() throws IOException {
        // update task list before exiting
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Task task: taskList) {
            writer.write(task.updateFile() + "\n");
        }
        writer.flush();
    }
}
