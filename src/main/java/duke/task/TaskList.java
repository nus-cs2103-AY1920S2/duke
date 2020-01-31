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
     */
    public void addTask(Task task) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        tasks.add(task);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes task from the task list.
     *
     * @param taskNo the index to delete from the ArrayList of tasks.
     * @throws DukeException if the given index is out of bound of the ArrayList.
     */
    public void deleteTask(int taskNo) throws DukeException {
        try {
            Task task = tasks.get(taskNo);
            tasks.remove(task);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + task);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Do you have this task number?");
        }
    }

    /**
     * Finds and lists all tasks that contains the keyword in the description.
     *
     * @param keyword the keyword to search for.
     */
    public void find(String keyword) {
        System.out.println("     Here are the matching tasks in your list:");
        int counter = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println("     " + counter + ". " + task);
                counter++;
            }
        }
    }

    /**
     * Lists all the tasks.
     */
    public void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
    }
}
