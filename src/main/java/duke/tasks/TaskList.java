/**
 * This serves as a wrapper to store an ArrayList of existing tasks.
 * This is also able to edit tasks in the ArrayList
 */
package duke.tasks;

import java.util.ArrayList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList object from an existing list of tasks
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates a TaskList object with a new list
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the ArrayList
     * @param t Task to be added to ArrayList
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Checks the number of existing tasks
     * @return Number of existing tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Prints to console the existing list of tasks
     * @param ui User interface to reply user
     */
    public String printTaskList(Ui ui) {
        StringBuilder output = new StringBuilder();
        if (size() == 0) {
            output.append("Your list of tasks is currently empty.\n");
        } else {
            output.append("This is your list of tasks:\n");
            int taskIdx = 1;
            for (Task t : taskList) {
                output.append(taskIdx + ". ");
                output.append(t.toString() + "\n");
                taskIdx++;
            }
        }
        return output.toString();
    }

    /**
     * Marks a task in the ArrayList as complete
     * @param ui User interface to reply user
     * @param storage To store updated task status in file
     * @param taskNumber Index of task to mark as completed
     * @throws DukeException If given index does not exist
     */
    public String completeTask(Ui ui, Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("This task number does not exist.");
        }
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        storage.updateTasks(taskList);
        return task.toString();
    }

    /**
     * Deletes a task in the ArrayList
     * @param ui User interface to reply user
     * @param storage To store updated task status in file
     * @param taskNumber Index of task to be deleted
     * @throws DukeException If given index does not exist
     */
    public String deleteTask(Ui ui, Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("This task number does not exist.");
        }
        Task task = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        storage.updateTasks(taskList);

        return task.toString();
    }

    /**
     * Returns a taskList object with descriptions containing the keyword
     * @param ui User interface to reply user
     * @param keyword To be searched against existing task list
     */
    public TaskList findKeyWord(Ui ui, String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
