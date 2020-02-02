package duke.task;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Represents a TaskList.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList when loading from disk.
     *
     * @param tasks the ArrayList of tasks from the disk.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks from TaskList.
     *
     * @return the ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds task to the task list.
     *
     * @param task the task to add.
     * @return acknowledgement message by Duke.
     */
    public String addTask(Task task) {
        String output = "     Got it. I've added this task:\n";
        output += "       " + task + "\n";
        tasks.add(task);
        output += "     Now you have " + tasks.size() + " tasks in the list.";
        return output;
    }

    /**
     * Deletes task from the task list.
     *
     * @param taskNo the index to delete from the ArrayList of tasks.
     * @return acknowledgement message by Duke.
     * @throws DukeException if the given index is out of bound of the ArrayList.
     */
    public String deleteTask(int taskNo) throws DukeException {
        try {
            Task task = tasks.get(taskNo);
            tasks.remove(task);
            return "     Noted. I've removed this task:\n       " + task
                    + "\n     Now you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Do you have this task number?");
        }
    }

    /**
     * Finds and lists all tasks that contains the keyword in the description.
     *
     * @param keyword the keyword to search for.
     * @return the list of tasks that contains the keyword.
     */
    public String find(String keyword) {
        String output = "     Here are the matching tasks in your list:";
        int counter = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                output += "\n     " + counter + ". " + task;
                counter++;
            }
        }
        return output;
    }

    /**
     * Lists all the tasks.
     *
     * @return the list of tasks.
     */
    public String listTasks() {
        String output = "     Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            output += "\n     " + (i + 1) + ". " + tasks.get(i);
        }
        return output;
    }
}
