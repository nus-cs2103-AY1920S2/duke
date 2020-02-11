package duke;

import java.util.ArrayList;

/**
 * Class for TaskList.
 */
public class TaskList {

    ArrayList<Task> todo = new ArrayList<>();

    /**
     * Task Constructor.
     * @param todo takes in arraylist of task
     */
    public TaskList(ArrayList<Task> todo) {
        this.todo = todo;
    }

    /**
     * Returns the size of task list.
     * @return size of the arraylist
     */
    public int getTaskListSize() {
        return todo.size();
    }

    /**
     * Gets the task.
     * @param i index of the task
     * @return the task object
     */
    public Task getTask(int i) {
        return todo.get(i);
    }

    /**
     * Removes a task.
     * @param i index of task to remove
     */
    public void removeTask(int i) {
        todo.remove(i);
    }

    /**
     * Adds in the task.
     * @param t takes in a task
     */
    public void addTask(Task t) {
        todo.add(t);
    }

    /**
     * Returns the entire list of task.
     * @return arraylist of task
     */
    public ArrayList<Task> getEntireList() {
        return todo;
    }
}
