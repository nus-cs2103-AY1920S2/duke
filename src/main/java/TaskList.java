import task.Task;

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
     * marks a specific Task in the list as done by parsing the string given as an integer.
     * @param str a string which tells us which Task we will be dealing with.
     * @throws IllegalArgumentException if the string is parsed into a number which is out of the range.
     */
    public void markDone(String str) throws IllegalArgumentException {
        int num = Integer.parseInt(str.substring(5));
        Parser parser = new Parser();
        Ui ui = new Ui();
        if (!parser.inRange(num, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        this.tasks.get(num - 1).markAsDone();
        ui.doneMessage(num, this.tasks);
    }

    /**
     * adds a Task to the TaskList.
     * @param t the Task being added.
     */
    public void addTask(Task t) {
        Ui ui = new Ui();
        tasks.add(t);
        ui.addMessage(t, tasks.size());
    }

    /**
     * deletes a Task in the TaskList by parsing the String given as an Integer.
     * @param str the String being parsed into an integer
     * @throws IllegalArgumentException if the string is parsed into a number which is out of the range.
     */
    public void delete(String str) throws IllegalArgumentException {
        Parser parser = new Parser();
        Ui ui = new Ui();
        int num = Integer.parseInt(str.substring(7));
        if (!parser.inRange(num, this.tasks.size())) {
            throw new IllegalArgumentException("OOPS!!! The number you checked for may not be valid.");
        }
        Task t = tasks.remove(num - 1);
        ui.deleteMessage(num, t, this.tasks.size());
    }

    /**
     * finds the tasks which contain the keyword given by the user and construct a TaskList by the resulting tasks.
     * @param str a String represents the input of the user.
     */
    public void find(String str) {
        Ui ui = new Ui();
        String keyWord = str.substring(5);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.getDescription().contains(keyWord)) {
                matchingTasks.add(t);
            }
        }
        TaskList matchingResults = new TaskList(matchingTasks);
        ui.getMatchingTasks(matchingResults);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
