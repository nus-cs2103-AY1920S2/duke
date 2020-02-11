/**
 * This serves as a wrapper to store an ArrayList of existing tasks.
 * This is also able to edit tasks in the ArrayList
 */
package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.MissingKeywordException;
import duke.exceptions.UnknownTaskException;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object from an existing list of tasks
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Creates a TaskList object with a new list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the ArrayList
     * @param t Task to be added to ArrayList
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Checks the number of existing tasks
     * @return Number of existing tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns existing ArrayList of tasks
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Prints to console the existing list of tasks
     */
    public String printTaskList() {
        StringBuilder output = new StringBuilder();
        if (size() == 0) {
            output.append("Your list of tasks is currently empty.\n");
        } else {
            output.append("This is your list of tasks:\n");
            int taskIdx = 1;
            for (Task task : tasks) {
                output.append(taskIdx + ". ");
                output.append(task.toString() + "\n");
                taskIdx++;
            }
        }
        return output.toString();
    }

    /**
     * Marks a task in the ArrayList as complete
     * @param storage To store updated task status in file
     * @param taskNumber Index of task to mark as completed
     * @throws DukeException If given index does not exist
     */
    public String completeTask(Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new UnknownTaskException();
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task.toString();
    }

    /**
     * Deletes a task in the ArrayList
     * @param storage To store updated task status in file
     * @param taskNumber Index of task to be deleted
     * @throws DukeException If given index does not exist
     */
    public String deleteTask(Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new UnknownTaskException();
        }
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        return task.toString();
    }

    /**
     * Returns a taskList object with descriptions containing the keyword
     * @param keyword To be searched against existing task list
     */
    public TaskList findKeyWord(String keyword) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() == 0) {
            throw new MissingKeywordException();
        }
        return new TaskList(matchingTasks);
    }
}
