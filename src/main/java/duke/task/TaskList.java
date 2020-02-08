package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * The type Task list.
 */
// Has operations to add or remove tasks in the list
public class TaskList {

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
    public void addToList(Task task) {
        list.add(task);
    }

    /**
     * Size of list int.
     *
     * @return the int
     */
    // Print out the size of the list
    public int sizeOfList() {
        return list.size();
    }

    /**
     * Remove from list.
     *
     * @param task the task
     * @throws DukeException the duke exception
     */
    // Delete the task from the list
    public void removeFromList(Task task) throws DukeException {
        try {
            list.removeIf(x -> x.equals(task));
            //list.remove(task);
        } catch (NoSuchElementException e) {
            throw new DukeException("This task does not exist in the list :( ");
        }
    }

    /**
     * Print elements.
     */
    // To print out every element in the list
    public String print_elements() {
        String test;
        test = list.stream().map(Task::toString)
                .collect(Collectors.joining(System.lineSeparator()));

        return test;
    }
}
