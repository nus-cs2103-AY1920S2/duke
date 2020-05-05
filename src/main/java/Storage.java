import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the TaskList
 */
public class Storage {

    Path filePath;
    Path folderPath;
    Storage() {
        String userHome = System.getProperty("user.home");
        filePath = Paths.get(userHome, "DukeData", "duke.txt");
        folderPath = Paths.get(userHome, "DukeData");
    }

    /**
     * Loads the TaskList that is stored in a predetermined filePath.
     * @return TaskList
     */
    public TaskList load() {
        TaskList taskList = null;
        try {
            createFileLocation();
            List<String> encodedTasks = Files.readAllLines(filePath, Charset.defaultCharset());
            taskList = this.decodeTaskList(encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Creates the directory and/or file if they have not been created yet
     * @throws IOException
     */
    public void createFileLocation() throws IOException {
        if (Files.notExists(folderPath)) {
            Files.createDirectory(folderPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Stores a Task List in a predetermined filePath
     * @param taskList the taskList object to be saved
     */
    public void save(TaskList taskList) {
        try {
            List<String> encodedTasks = this.encodeTaskList(taskList);
            Files.write(filePath, encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an encoded String representation of TaskList so that it can be stored
     * @return a String representation of TaskList
     */
    public List<String> encodeTaskList(TaskList taskList) {
        List<String> encodedTasks = new ArrayList<>();
        for (Task t : taskList.getList()) {
            encodedTasks.add(t.encodeTask());
        }
        return encodedTasks;
    }

    /**
     * Returns a decoded TaskList so that it can be used by Duke
     * If the text is an empty String, an empty ArrayList will be returned as expected.
     * @param encodedTasks the encoded text used to store the TaskList
     * @return a TaskList based on the encoded text
     */
    public TaskList decodeTaskList(List<String> encodedTasks) {
        List<Task> decodedTasks = new ArrayList<>();
        for (String s : encodedTasks) {
            switch(s.charAt(0)) {
                case ('T'):
                    decodedTasks.add(Todo.decode(s));
                    break;
                case ('D'):
                    decodedTasks.add(Deadline.decode(s));
                    break;
                case ('E'):
                    decodedTasks.add(Event.decode(s));
                    break;
                default:
                    break;
            }
        }
        return new TaskList(decodedTasks);
    }

}
