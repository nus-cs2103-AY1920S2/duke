package duke.main;

import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {


    private ArrayList<Task> tasks;

    /**
     * Constructor for the class that stores all the tasks.
     * @param tasks is the tasks to be stored in the TaskList object
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Check if there is any tasks stored in the TaskList object.
     * @return if there is any tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Check the size of this instance of TaskList.
     * @return the size of the duke.main.TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the indexed task from TaskList.
     * @param i is the index that user want
     * @return the indexed task
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds the task to the current instance of duke.main.TaskList.
     * @param task is the task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task to the current instance of duke.main.TaskList.
     * @param toDelete is the index of task to be removed
     */
    public void remove(int toDelete) {
        tasks.remove(toDelete);
    }


    /**
     * Finds and returns all the tasks with the keyword.
     * @param keyword is the keyword that we want to search amongst all the tasks
     * @return an ArrayList of all the tasks with the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    public void sortByAlphabeticalOrder() {
        tasks.sort(Comparator.comparing(Task::toString));
    }
}
