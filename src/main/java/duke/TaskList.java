package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /** Stores a list of tasks. */
    private final List<Task> taskList;

    /** Constructs a new TaskList that can store an arbitrary size. */
    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param taskId the position of the task in the list, starting from #1.
     * @return the task at the specified position in the list.
     */
    public Task get(int taskId) {
        return taskList.get(taskId - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getNumTasks() {
        return taskList.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getList() {
        return new ArrayList<>(taskList);
    }

    /**
     * Adds a new tasks into the TaskList.
     *
     * @param task the task to add into the list.
     * @return a copy of this TaskList with the newly added task.
     */
    public TaskList addTask(Task task) {
        List<Task> newList = new ArrayList<>(taskList);
        newList.add(task);

        return new TaskList(newList);
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param taskId the id of the task in the list.
     * @return a copy of this TaskList with the specified task marked as done.
     */
    public TaskList doneTask(int taskId) {
        List<Task> newList = new ArrayList<>(taskList);

        Task newTask = taskList.get(taskId - 1).markDone();
        newList.set(taskId - 1, newTask);

        return new TaskList(newList);
    }

    /**
     * Deletes a task in the TaskList.
     *
     * @param taskId the id of the task in the list.
     * @return a copy of this TaskList with the specified task removed,
     *      and remaining elements left-shifted.
     */
    public TaskList deleteTask(int taskId) {
        List<Task> newList = new ArrayList<>(taskList);
        newList.remove(taskId - 1);

        return new TaskList(newList);
    }

    @Override
    public String toString() {
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%2d.%s\n", i + 1, taskList.get(i)));
        }

        if (tasks.length() > 0) {
            tasks.deleteCharAt(tasks.length() - 1);
        }

        return tasks.toString();
    }
}
