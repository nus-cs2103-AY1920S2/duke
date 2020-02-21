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
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) { // initialise the task list with files loaded from storage
        this.taskList = taskList;
    }


    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber);
    }

    /**
     * Deletes task from taskList.
     *
     * @param taskNumbers an int[] of indices to delete.
     * @return the deleted task
     * @throws DukeException
     */
    public ArrayList<Task> deleteTasks(int... taskNumbers) throws DukeException { // deletes task at the index provided, and returns the deleted task
        ArrayList<Task> deletedTasks = new ArrayList<>();
        try {
            Arrays.sort(taskNumbers);
            for (int i = taskNumbers.length- 1; i >= 0; i--) {
                Task task = taskList.remove(i);
                deletedTasks.add(task);
            }
        } catch (IndexOutOfBoundsException e) { // throw exception if the task does not exist at index provided
            throw new DukeException(DukeError.NUMBER);
        }
        return deletedTasks;
    }

    /**
     * Marks task as done in the taskList.
     *
     * @param index the index where the task to be marked done is at in the ArrayList
     * @return the task marked as done
     * @throws DukeException
     */
    public Task doneTask(int index) throws DukeException { // marks the task at the index provided as done, and returns the done task
        try {
            Task task;
            task = taskList.get(index);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) { // throw exception if the task does not exist at index provided
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
