package duke.model;

import duke.tasks.Task;
import duke.utils.StringParser;

import java.util.List;
import java.util.ArrayList;

/**
 * The <code>TaskList</code> class stores a list of all <code>Task</code> objects and handles
 * them.
 *
 * @author Zhu Yijie
 */
public class TaskList {
    private List<Task> tasks;
    
    /**
     * Creates a new TaskLit object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    /**
     * Creates a new TaskLit object.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Adds a task to the list.
     *
     * @param newTask The task to be added.
     * @throws IllegalArgumentException If the task already exists in the list.
     */
    public void add(Task newTask) throws IllegalArgumentException {
        if (tasks.contains(newTask)) {
            throw new IllegalArgumentException();
        }
        
        tasks.add(newTask);
    }
    
    /**
     * Gets a task by its id (index + 1).
     *
     * @param id Id of task that user sees.
     * @return The task.
     * @throws IndexOutOfBoundsException If id is invalid.
     */
    private Task get(int id) throws IndexOutOfBoundsException {
        if (id <= 0 || id > tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        return tasks.get(id - 1);
    }
    
    /**
     * Removes a task from the list.
     *
     * @param id The id of the task to be removed.
     * @return The task removed.
     */
    public Task remove(int id) {
        Task task = get(id);
        tasks.remove(task);
        return task;
    }
    
    /**
     * Marks a task from the list as completed.
     *
     * @param id The id of the task to be modified.
     * @return The task modified.
     */
    public Task markAsCompleted(int id) {
        Task task = get(id);
        task.markAsCompleted();
        return task;
    }
    
    /**
     * Marks a task from the list as incomplete.
     *
     * @param id The id of the task to be modified.
     * @return The task modified.
     */
    public Task markAsIncomplete(int id) {
        Task task = get(id);
        task.markAsIncomplete();
        return task;
    }
    
    /**
     * Finds an array of tasks that match the string provided. Returns null if there are no
     * matching tasks.
     *
     * @param name The name of task to find.
     * @return Task array, if found, or null if no matching tasks found.
     */
    public Task[] find(String name) {
        List<Task> tasksMatched = new ArrayList<>();
        for (Task task : tasks) {
            if (task.matchPartial(name)) {
                tasksMatched.add(task);
            }
        }
        
        if (tasksMatched.isEmpty()) {
            return null;
        } else {
            return tasksMatched.toArray(Task[]::new);
        }
    }
    
    /**
     * Lists all tasks in the list.
     *
     * @return String representing the list of tasks.
     */
    public String list() {
        String output = "";
        for (int i = 0; i < tasks.size(); ++i) {
            output += ((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return output;
    }
    
    /**
     * Formats the list of tasks for storing in a file.
     *
     * @return String representing the list of tasks.
     */
    public String formatForStorage() {
        String[] taskStrings = tasks.stream()
                .map(Task::format)
                .toArray(String[]::new);
        return StringParser.combineTextWithNewline(taskStrings);
    }
}
