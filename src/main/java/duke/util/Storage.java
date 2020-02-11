package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from the stored data file and saving tasks in the data file.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes a <code>Storage</code> class with the given file path.
     * @param filePath The path of the data file in which the existing tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task parseTask(String str) {
        String[] taskElements = str.split(" # ");
        String type = taskElements[0];
        String isDone = taskElements[1];
        String description = taskElements[2];
        String time = (taskElements.length > 3) ? taskElements[3] : "";

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, time);
            break;
        case "E":
            task = new Event(description, time);
            break;
        default:
            task = new Task("default");
        }
        if (isDone.equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads the tasks from the data file.
     * @return A tasklist that contains all the existing tasks.
     * @throws FileNotFoundException If the data file is not found.
     */
    public TaskList loadTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            tasks.add(parseTask(s.nextLine()));
        }
        return new TaskList(tasks);
    }

    private void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    private String tasksToString(TaskList tasklist) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> tasks = tasklist.getTasks();
        for (Task task : tasks) {
            sb.append(task.toStringInFile()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Writes the given tasklist into the data file.
     * @param tasklist The tasklist that needs to be written.
     */
    public void updateTaskData(TaskList tasklist) {
        try {
            writeToFile(tasksToString(tasklist));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
