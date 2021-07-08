package duke.utilities;

import duke.tasks.Task;
import duke.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A class to add, delete and mark tasks as done.
 * TaskList object is passed to the execute method of Command.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for the taskList object, called in the main Duke class.
     *
     * @param taskList an ArrayList of tasks loaded from Storage
     */
    public TaskList(ArrayList<Task> taskList) { // initialise the task list with files loaded from storage
        this.taskList = taskList;
    }

    /**
     * A method to retrieve the task at the given index.
     *
     * @param taskNumber the index the task to retrieve is at
     * @return Task object from the taskList
     */
    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber);
    }

    /**
     * Deletes task from taskList.
     *
     * @param taskNumbers an int[] of indices to delete.
     * @return ArrayList of the tasks deleted
     * @throws DukeException error if the task does not exist at the index
     */
    public ArrayList<Task> deleteTasks(int... taskNumbers) throws DukeException {
        ArrayList<Task> deletedTasks = new ArrayList<>();
        try {
            Arrays.sort(taskNumbers);
            for (int i = taskNumbers.length - 1; i >= 0; i--) {
                Task task = taskList.remove(taskNumbers[i]);
                deletedTasks.add(task);
            }
        } catch (IndexOutOfBoundsException e) { // throw DukeException
            throw new DukeException(DukeError.NUMBER);
        }
        Collections.reverse(deletedTasks);
        return deletedTasks;
    }

    /**
     * Marks task as done in the taskList.
     *
     * @param index the index where the task to be marked done is at in the ArrayList
     * @return the task marked as done
     * @throws DukeException if the task does not exist at the index
     */
    public Task doneTask(int index) throws DukeException { // marks the task at the index provided as done, and returns the done task
        try {
            Task task;
            task = taskList.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) { // throw DukeException
            throw new DukeException(DukeError.NUMBER);
        }
    }

    /**
     * Updates task description or date
     */
    public Task updateTask(int index, Task task) {
        taskList.set(index, task);
        return task;
    }

    /**
     * Adds given task to the ArrayList.
     *
     * @param task task to be added
     * @return a boolean True if successfully added
     */
    public boolean addTask(Task task) {
        return taskList.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getListSize() {
        return taskList.size();
    }
}
