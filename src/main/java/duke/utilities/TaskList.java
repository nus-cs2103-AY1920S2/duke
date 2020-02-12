package duke.utilities;

import duke.tasks.Task;
import duke.exceptions.*;

import java.util.ArrayList;

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
     * @param index the index where the task to be removed is at in the ArrayList
     * @return the deleted task
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException { // deletes task at the index provided, and returns the deleted task
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) { // throw exception if the task does not exist at index provided
            throw new DukeException(DukeError.NUMBER);
        }
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
