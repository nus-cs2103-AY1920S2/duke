package dukeproj.data;

import dukeproj.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks using ArrayList.
 */
public class TaskList {
    /** Data structure used to represent list. */
    private ArrayList<Task> tasks;

    /**
     * Gets the size of the Task List.
     *
     * @return size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds the task provided into TaskList.
     *
     * @param task task to add into list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a certain task in the TaskList based upon the index provided.
     *
     * @param idx index of task to get.
     * @return task from list.
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Removes the task at the specified index in TaskList.
     *
     * @param idx index of task to remove.
     */
    public void removeTask(int idx) {
        tasks.remove(idx);
    }

    /**
     * Gets the ArrayList variable in TaskList.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Checks if the TaskList contains a specified Task.
     *
     * @param task the task to be checked for.
     * @return boolean whether the list has the specified Task.
     */
    public boolean hasTask(Task task) {
        return tasks.contains(task);
    }

    /**
     * Finds tasks in the TaskList using a varargs of keywords.
     *
     * @param keywords The varargs of keywords used to find tasks.
     * @return The ArrayList of tasks found using the keywords.
     */
    public ArrayList<Task> find(String...keywords) {
        ArrayList<Task> outputList = new ArrayList<>();
        for (Task task: tasks) {
            for (String str: keywords) {
                if (task.getTask().contains(str) && !outputList.contains(task)) {
                    outputList.add(task);
                }
            }
        }
        return outputList;
    }

    /**
     * Constructs a TaskList based on an existing ArrayList.
     *
     * @param tasks an ArrayList of tasks to be added into TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the String representation of task list.
     *
     * @return TaskList in the form of a String.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }
}
