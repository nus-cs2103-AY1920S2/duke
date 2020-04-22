package duke.storage;

import duke.commons.Deadline;
import duke.commons.Event;
import duke.commons.Task;
import duke.commons.Todo;
import duke.logic.TaskList;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Represents the storage manager that loads <code>Task</code> objects from the data file and
 * saves <code>Task</code> objects to the data file.
 */
public class Storage {

    private String filePath;
    private static final String SPLIT =  " \\| ";
    private static final String JOIN = " | ";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an <code>ArrayList</code> of <code>Task</code> objects loaded from the data file.
     *
     * @return an <code>ArrayList</code> of <code>Task</code> objects loaded from the data file.
     * @throws IOException If the <code>Task</code> objects could not be loaded.
     */
    public TaskList load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            initialiseData();
            reader = new BufferedReader(new FileReader(filePath));
        }
        String line = "";
        line = reader.readLine();

        while (line != null) {
            String[] tokens = line.split(SPLIT);
            String type = tokens[0];
            boolean isDone = Boolean.parseBoolean(tokens[1]);
            String description = tokens[2];
            Task task;
            if (type.equals("T")) {
                task = new Todo("todo", isDone, description);
            } else {
                // Deadline/Event has tokens = {type, isDone, description, time}
                assert tokens.length == 4 : "missing argument(s)";
                String time = tokens[3];
                if (type.equals("D")) {
                    task = new Deadline("deadline", isDone, description, time);
                } else {
                    // Event type is provided
                    assert type.equals("E") : "invalid type";
                    task = new Event("event", isDone, description, time);
                }
            }
            assert task != null : "null task";
            taskList.add(task);
            line = reader.readLine();
        }

        reader.close();
        return new TaskList(taskList);
    }

    /**
     * Saves the current <code>Task</code> in the data file.
     *
     * @param tasks <code>TaskList</code> object of the program.
     * @throws IOException If the <code>Task</code> objects could not be saved.
     */
    public void update(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task: taskList) {
            String[] tokens = task.toDataTokens();
            writer.append(String.join(JOIN, tokens));
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    /**
     * Creates a new data file called "tasks.txt" if such file does not exist when the program first starts.
     */
    public void initialiseData() {
        File file = new File(".//tasks.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // initialiseData() only called when file not already exists
            assert 1 == 0 : "data already exists";
        }
    }
}
