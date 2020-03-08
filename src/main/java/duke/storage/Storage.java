package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */

public class Storage {
    protected String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads contents in text file into TaskList.
     * @return TaskList for Duke.
     * @throws IOException thrown when error encountered.
     */
    public ArrayList<Task> loadFile() throws IOException {
        File file = new File(path); //creates a file for the given file path
        file.getParentFile().mkdir(); //creating for jar file
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] taskArray = task.split(" \\| ");
                String type = taskArray[0];
                switch (type) {
                    case "T":
                        Todo todo = new Todo(taskArray[2]);
                        if (taskArray[1].equals("1")) {
                            todo.markDone();
                        }
                        list.add(todo);
                        break;

                    case "D":
                        Deadline deadline = new Deadline(taskArray[2], taskArray[3]);
                        if (taskArray[1].equals("1")) {
                            deadline.markDone();
                        }
                        list.add(deadline);
                        break;

                    case "E":
                        Event event = new Event(taskArray[2], taskArray[3], taskArray[4]);
                        if (taskArray[1].equals("1")) {
                            event.markDone();
                        }
                        list.add(event);
                        break;

                    default:
                        break;
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            file.createNewFile();
        }
        return list;
    }

    /**
     * Writes a new ArrayList of tasks.
     * @param taskList List of tasks for Duke.
     * @throws IOException thrown when error is encountered.
     */
    public void writeNew(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : taskList.list) {
            fw.write(task.formatText());
        }
        fw.close();
    }

    /**
     * Writes to the old ArrayList of tasks.
     * @param newTask New task added.
     * @throws IOException thrown when error is encountered.
     */
    public void writeToOld(String newTask) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(newTask);
        fw.close();
    }
}

