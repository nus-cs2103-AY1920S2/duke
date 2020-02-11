package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 *  A duke.TaskList contains the duke.task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * construct a duke.TaskList by the list of Tasks given.
     * @param tasks a list of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * checks whether the number input is valid.
     * @param num the number given by the user.
     * @param index the size of the duke.TaskList.
     * @return true if num is within the correct range.
     */
    public boolean inRange(int num, int index) {
        return num <= 0 || num > index;
    }

    /**
     * marks a specific Task in the list as done by parsing the string given as an integer.
     * @param requestNumber a string which tells us which Task we will be dealing with.
     * @throws IllegalArgumentException if the string is parsed into a number which is out of the range.
     */
    public void markDone(int requestNumber) throws IllegalArgumentException {
        if (inRange(requestNumber, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        this.tasks.get(requestNumber - 1).markAsDone();
    }

    /**
     * adds a Task to the duke.TaskList.
     * @param t the Task being added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * deletes a Task in the duke.TaskList by parsing the String given as an Integer.
     * @param requestNumber the String being parsed into an integer
     * @throws IllegalArgumentException if the string is parsed into a number which is out of the range.
     */
    public Task delete(int requestNumber) throws IllegalArgumentException {
        if (inRange(requestNumber, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        return tasks.remove(requestNumber - 1);
    }

    /**
     * finds the tasks which contain the keyword given by the user and construct a duke.TaskList by the resulting tasks.
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

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }
}
