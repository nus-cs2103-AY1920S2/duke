package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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

        // Solution below adapted from https://github.com/Kangwkk/duke/commit/512324ce4fbb6ef7aa5b54650a9ca1d5f872d76d
        try {
            if (!(new File(filePath)).exists()) {
                String rootLocation = Paths.get("").toAbsolutePath().toString();
                StringTokenizer st = new StringTokenizer(filePath);
                String newDirectoryLocation = rootLocation + File.separator + st.nextToken("/");
                String newFileLocation = newDirectoryLocation + File.separator + st.nextToken("/");
                Files.createDirectories(Paths.get(newDirectoryLocation));
                Files.createFile(Paths.get(newFileLocation));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
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
            assert false : type;
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
