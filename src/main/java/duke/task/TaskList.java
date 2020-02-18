package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TaskList {
    /** Stores a list of tasks. */
    private final List<Task> tasks;
    private final HashSet<Task> taskCounters;

    /** Constructs a new TaskList that can store an arbitrary size. */
    public TaskList() {
        this(new ArrayList<>(), new HashSet<>());
    }

    /** Constructs a new TaskList using an existing list of tasks. */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;

        this.taskCounters = new HashSet<>();
        this.taskCounters.addAll(tasks);

    }

    private TaskList(List<Task> tasks, HashSet<Task> taskCounters) {
        this.tasks = tasks;
        this.taskCounters = taskCounters;
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param taskId the position of the task in the list, starting from #1.
     * @return the task at the specified position in the list.
     */
    public Task get(int taskId) {
        return tasks.get(taskId - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Adds a new tasks into the TaskList.
     *
     * @param task the task to add into the list.
     * @return a copy of this TaskList with the newly added task.
     */
    public TaskList addTask(Task task) throws DukeException {
        if (taskCounters.contains(task)) {
            throw new DukeException("Duplicate entered. Please try again!");
        }
        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.add(task);

        HashSet<Task> newTaskCounters = new HashSet<>(taskCounters);
        newTaskCounters.add(task);

        return new TaskList(newTasks, newTaskCounters);
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param taskId the id of the task in the list.
     * @return a copy of this TaskList with the specified task marked as done.
     */
    public TaskList doneTask(int taskId) {
        Task oldTask = this.get(taskId);
        Task newTask = oldTask.markDone();

        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.set(taskId - 1, newTask);

        // For safety, update hashSet with the completed task
        HashSet<Task> newTaskCounters = new HashSet<>(taskCounters);
        newTaskCounters.remove(oldTask);
        newTaskCounters.add(newTask);

        return new TaskList(newTasks, newTaskCounters);
    }

    /**
     * Deletes a task in the TaskList.
     *
     * @param taskId the id of the task in the list.
     * @return a copy of this TaskList with the specified task removed,
     *      and remaining elements left-shifted.
     */
    public TaskList deleteTask(int taskId) {
        Task task = this.get(taskId);

        List<Task> newTasks = new ArrayList<>(tasks);
        newTasks.remove(taskId - 1);

        HashSet<Task> newTaskCounters = new HashSet<>(taskCounters);
        newTaskCounters.remove(task);

        return new TaskList(newTasks, newTaskCounters);
    }

    /**
     * Returns a task in the list as a formatted text.
     *
     * @param taskId the id of the task in the list.
     * @return a task in the list as a formatted text.
     */
    public String getFormattedTask(int taskId) {
        String description = tasks.get(taskId - 1).toFormatString();

        int paramIndex = description.indexOf("\n");

        if (paramIndex >= 0) {
            String indent = "      ";

            String parameters = description.substring(paramIndex + 1);
            parameters = parameters.replaceAll("(?m)^", indent);

            description = description.substring(0, paramIndex);

            return String.format("%4d. %s\n%s", taskId, description, parameters);

        } else {
            return String.format("%4d. %s", taskId, description);
        }
    }

    @Override
    public String toString() {
        StringBuilder taskDescriptions = new StringBuilder();
        String spacing = "\n\n";

        for (int i = 1; i <= this.tasks.size(); i++) {
            taskDescriptions.append(getFormattedTask(i)).append(spacing);
        }

        if (taskDescriptions.length() > 0) {
            int stringLength = taskDescriptions.length();
            taskDescriptions.delete(stringLength - spacing.length(), stringLength);
        }

        return taskDescriptions.toString();
    }
}
