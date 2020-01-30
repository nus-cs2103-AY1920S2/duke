package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import duke.tasks.*;
import duke.exception.DukeException;

/**
 * Provides functionality for reading and writing from storage file.
 */
public class Storage {
    private File data;

    public Storage(String s) {
        try {
            this.data = new File(s);
        } catch(Exception e) {
            System.err.println("Error retrieving file!");
        }
    }

    /**
     * Builds a TaskList class from storage Filepath.
     * @return Returns a TaskList class built according to storage Filepath. Returns an empty TaskList class
     * if there is no storage file.
     * @throws DukeException Filepath not found.
     */
    public TaskList buildTaskList() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(data);
            while(scan.hasNext()) {
                String elementLine = scan.nextLine();
                String[] elements = elementLine.split("\\|");
                switch(elements[0]) {
                case "T":
                    taskList.add(Todo.create(elements));
                    break;
                case "E":
                    taskList.add(Event.create(elements));
                    break;
                case "D":
                    taskList.add(Deadline.create(elements));
                    break;
                default:
                    taskList.add(Task.create(elements));
                    break;
                }
            }
            scan.close();
            return new TaskList(taskList);
        } catch(Exception e) {
            throw new DukeException("Error building task list from file path. Creating new task.txt");
        }           
    }

    /**
     * Saves given TaskList to the storage file.
     * @param taskList The TaskList to be stored.
     */
    public void updateStorage(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(data);
            for (Task t: taskList.getTaskList()) {
                writer.write(t.store() + "\n");
            }
            writer.close();
        } catch(Exception e) {
            System.err.println("Error writing to save file, message: " + e.toString());
        }
        return;
    }
}
