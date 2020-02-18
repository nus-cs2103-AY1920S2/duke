import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage object to read and write from and to a file in the given file path.
 */

public class Storage {
    String filePath;
    File file;
    static final String TODO_INITIAL = "T";
    static final String EVENT_INITIAL = "E";
    static final String DEADLINE_INITIAL = "D";

    /**
     * Constructor. Links this storage object to a specific file path. Create a file if it does not exist.
     * @param filePath Path of the file.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
            this.file = new File(this.filePath);
        }
    }

    /**
     * Generate TaskList from file obtained from filePath.
     * @return TaskList generated from file obtained from filePath
     * @throws IOException Throws error when there is error with I/O
     */
    public TaskList getTaskList() throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            parseLine(s.nextLine(), taskList);
        }
        return taskList;
    }

    /**
     * Helper method to parse each line into a Task.
     * @param taskString Line of input to be parsed
     * @param taskList TaskList to add the Task to
     */
    public void parseLine(String taskString, TaskList taskList) {
        assert taskString.length() > 0;
        String[] taskDetails = taskString.split(",");
        for (int i = 0; i < taskDetails.length; i++) {
            taskDetails[i] = taskDetails[i].trim();
        }
        String taskType = taskDetails[0];
        boolean isTaskDone = taskDetails[1].equals("1");
        String taskName = taskDetails[2];

        if (taskType.equals(TODO_INITIAL)) {
            Task newTask = new ToDo(taskName);
            newTask.done = isTaskDone;
            taskList.arrList.add(newTask);
        } else if (taskType.equals(DEADLINE_INITIAL)) {
            String taskTime = taskDetails[3];
            Task newTask = new Deadline(taskName, taskTime);
            newTask.done = isTaskDone;
            taskList.arrList.add(newTask);
        } else if (taskType.equals(EVENT_INITIAL)) {
            String taskTime = taskDetails[3];
            Task newTask = new Event(taskName, taskTime);
            newTask.done = isTaskDone;
            taskList.arrList.add(newTask);
        }
    }

    /**
     * Updates the file contents based on the taskList given.
     * @param taskList TaskList to be written into the file
     * @throws IOException Throws error when there is error with I/O
     */
    public void updateFile(TaskList taskList) throws IOException {
        String textToAdd = "";
        for (Task t : taskList.arrList) {
            textToAdd += t.toFile() + "\n";
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
