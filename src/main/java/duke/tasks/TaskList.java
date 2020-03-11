package duke.tasks;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Encapsulates a list of tasks.
 * Methods are provided to insert, get and remove tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    
    /**
     * Constructs an empty `TaskList` object.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }
    
    /**
     * Constructs a `TaskList` object representing an `ArrayList&lt;Task&gt;` list of tasks.
     * @param tasks List of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Returns the underlying list of tasks represented by this object.
     * @return List of tasks
     */
    public ArrayList<Task> getTaskState() {
        return tasks;
    }
    
    /**
     * Returns the 0-indexed index-th task.
     * @param index Index of task, 0-indexed
     * @return index-th task
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    /**
     * Adds a task to the bottom of the task list.
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    /**
     * Removes the 0-indexed index-th task from the task list.
     * @param index Index of task, 0-indexed to be removed
     * @return The removed task
     */
    public Task removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }
    
    /**
     * Marks the 0-indexed index-th task as completed.
     * @param index Index of task, 0-indexed
     */
    public void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }
    
    /**
     * Returns the number of tasks in the task list.
     * @return Number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Sorts the tasks in the list in ascending task date.
     * Tasks without a date (e.g. `ToDo`) will be placed after all tasks with a date.
     */
    public void sort() {
        Collections.sort(tasks, (taskA, taskB) -> {
            if (taskA instanceof DatedTask) {
                if (taskB instanceof DatedTask) {
                    DatedTask u = (DatedTask)taskA;
                    DatedTask v = (DatedTask)taskB;
                    //return ((DatedTask)taskA).getDate().compare(((DatedTask)taskB).getDate());
                    return u.getDate().compareTo(v.getDate());
                } else {
                    return -1;
                }
            }
            
            if (taskB instanceof DatedTask) {
                return 1;
            } else {
                //Relative ordering of taskA and taskB is preserved
                //since Collections.sort is a stable sort
                return 0;
            }
        });
    }
}
