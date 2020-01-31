package duke;

import duke.tasks.Task;
import duke.Storage;
import duke.exception.DukeException;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Task list that stores all tasks specified by user.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Creates an empty task list
     */
    public TaskList() {
        this.list = new ArrayList<>();;
    }

    /**
     * Creates a task list.
     *
     * @param list list of tasks to be stored in the task list.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds task to the task list.
     *
     * @param task task to be added to the task list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns task in task list requested by user.
     *
     * @param index index position of task in the task list.
     * @return Task requested by user.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Return formatted string that contains all tasks in the task list.
     *
     * @return formatted string message containing all tasks in task list.
     */
    public String saveList() {
        String toSave = "";
        for (int i = 0; i < list.size(); i++) {
            toSave += list.get(i).toPrint();
            toSave += "\n";
        }
        return toSave;
    }

    /**
     * Returns number of tasks in the task list.
     *
     * @return integer representing number of tasks in task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + list.get(i));
        }
    }

    /**
     * Prints the number of tasks present in task list.
     */
    public void printSize() {
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Marks task in task list as completed.
     *
     * @param index index position of task in task list to be marked as completed.
     * @param storage makeshift database for tasks.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be open for any other reason.
     */
    public void done(int index, Storage storage) throws IOException {
        list.get(index).markDone();
        storage.writeToFile(saveList());
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + list.get(index));
    }

    /**
     * Deletes task from task list.
     *
     * @param index index position of task to be deleted from task list.
     * @param storage makeshift database for tasks.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be open for any other reason.
     * @throws DukeException if user input does not follow input format.
     */
    public void delete(int index, Storage storage) throws IOException, DukeException {
        if (size() <= index) {
            throw new DukeException("There is no task " + (index + 1) + ".");
        }
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + list.get(index));
        list.remove(index);
        storage.writeToFile(saveList());
    }
}