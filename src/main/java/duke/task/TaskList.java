package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import duke.common.Messages;
import duke.exception.TaskIndexOutOfBoundException;

/**
 * Represents the list of Task in the application.
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Constructs a TaskList with the specified list of task.
     *
     * @param taskList The list of task to be included in this TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the current size of this TaskList.
     *
     * @return The current size of this TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the Task in this TaskList with the specified task index.
     *
     * @param taskIndex The index of the Task to be returned.
     * @return The Task in the list with the specified index.
     * @throws TaskIndexOutOfBoundException If the specified task index is not within bound of this TaskList.
     */
    public Task get(int taskIndex) throws TaskIndexOutOfBoundException {
        try {
            return taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundException(Messages.generateTaskIndexNotFoundMessage(taskIndex + 1));
        }
    }

    /**
     * Adds the specified Task into this TaskList.
     *
     * @param task The Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Inserts the specified Task into this TaskList at the specified index.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to
     * their indices).
     *
     * @param index The index at which the specified Task is to be inserted.
     * @param task  The Task to be inserted.
     */
    public void add(int index, Task task) {
        taskList.add(index, task);
    }

    /**
     * Removes the Task in this TaskList with the specified task index and then returns it.
     *
     * @param taskIndex The index of the Task to be removed.
     * @return The Task that is removed.
     * @throws TaskIndexOutOfBoundException If the specified task index is not within bound of this TaskList.
     */
    public Task remove(int taskIndex) throws TaskIndexOutOfBoundException {
        try {
            return taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundException(Messages.generateTaskIndexNotFoundMessage(taskIndex + 1));
        }
    }

    /**
     * Returns a Stream object containing Tasks in this TaskList.
     *
     * @return a Stream object containing Tasks in this TaskList.
     */
    public Stream<Task> stream() {
        return taskList.stream();
    }
}
