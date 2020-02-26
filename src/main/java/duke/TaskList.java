package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 *  A TaskList contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * construct a TaskList by the list of Tasks given.
     * @param tasks a list of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * checks whether the number input is valid.
     * @param num the number given by the user.
     * @param index the size of the TaskList.
     * @return true if num is within the correct range.
     */
    public boolean inRange(int num, int index) {
        return num <= 0 || num > index;
    }

    /**
     * marks a specific Task in the list as done.
     * @param requestNumber the index of the Task we will be dealing with.
     * @throws IllegalArgumentException if index is out of range.
     */
    public void markDone(int requestNumber) throws IllegalArgumentException {
        if (inRange(requestNumber, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        this.tasks.get(requestNumber - 1).markAsDone();
    }

    /**
     * adds a Task to the TaskList.
     * @param t the Task being added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * deletes a Task in the TaskList.
     * @param requestNumber the index of the Task we are dealing with.
     * @throws IllegalArgumentException if the index is out of range.
     */
    public Task delete(int requestNumber) throws IllegalArgumentException {
        if (inRange(requestNumber, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        return tasks.remove(requestNumber - 1);
    }

    /**
     * finds the tasks which contain the keyword given by the user and construct a TaskList by the resulting tasks.
     * @param keyWord a String represents the input of the user.
     */
    public TaskList find(String keyWord) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getDescription().contains(keyWord)) {
                matchingTasks.add(t);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * tags a Task in the TaskList by parsing the String given as an Integer.
     * @param requestNumber the index of the Task we are dealing with.
     * @param tag name of the tag.
     * @throws IllegalArgumentException if the index is out of range.
     */
    public void tagTask(int requestNumber, String tag) throws IllegalArgumentException {
        if (inRange(requestNumber, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        this.tasks.get(requestNumber - 1).addTag(tag);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * gets the specific Task given the index of the Task.
     * @param num the index of the Task.
     * @return the Task in the TaskList of that index.
     */
    public Task getTask(int num) {
        return getTasks().get(num - 1);
    }

    /**
     * gets the number of Tasks in this TaskList.
     * @return the number of Tasks in this TaskList.
     */
    public int size() {
        return this.tasks.size();
    }
}
