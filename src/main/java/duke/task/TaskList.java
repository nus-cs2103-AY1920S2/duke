package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The type Task list.
 */
// Has operations to add or remove tasks in the list
public class TaskList{

    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Gets list.
     *
     * @return the list
     */
// Getting the list of the task.
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Add to list.
     *
     * @param task the task
     */
// Add the task to the list
    public void add_to_list(Task task) {
        list.add(task);
    }

    /**
     * Size of list int.
     *
     * @return the int
     */
// Print out the size of the list
    public int size_of_list() {
        return list.size();
    }

    /**
     * Remove from list.
     *
     * @param task the task
     * @throws DukeException the duke exception
     */
// Delete the task from the list
    public void remove_from_list(Task task) throws DukeException {
        try {
            list.remove(task);
        } catch (NoSuchElementException e) {
            throw new DukeException("This task does not exist in the list :( ");
        }
    }

    /**
     * Print elements.
     */
// To print out every element in the list
    public void print_elements() {
        for (int i = 0; i < list.size(); i++) {
            String space = "        ";
            System.out.println( space + (i + 1) + "." + list.get(i));
        }
    }
}
