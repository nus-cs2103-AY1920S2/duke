package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object, which maintains an array list of Task objects.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets Task objects from the TaskList class instance.
     *
     * @param index int. The index of the task to be returned
     * @return Task object at the specified index in the TaskList
     */
    public Task getTask(int index) {
        // Asserts that there is a Task object at the specified index
        assert tasks.size() > index;

        return tasks.get(index);
    }

    /**
     * Appends a single Task object to the TaskList instance.
     *
     * @param newTask Task object to be appended
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes the Task object at the specified index.
     *
     * @param index int. Index of the Task to be removed
     * @return Task object that has been removed
     */
    public Task removeTask(int index) {
        // Asserts that there is a Task object at the specified index
        assert tasks.size() > index;

        return tasks.remove(index);
    }

    /**
     * Retrieves the number of elements in the TaskList.
     *
     * @return int the number of elements
     */
    public int size() {
        return tasks.size();
    }

}
