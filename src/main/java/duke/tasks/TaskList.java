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
    public void printTaskList(Ui ui) {
        ui.showLine();
        if (size() == 0) {
            ui.print("Your list of tasks is currently empty.");
        } else {
            ui.print("This is your list of tasks:");
            for (Task t : taskList) {
                ui.print(t.toString());
            }
        }
    }

    /**
     * Marks a task in the ArrayList as complete
     * @param ui User interface to reply user
     * @param storage To store updated task status in file
     * @param taskNumber Index of task to mark as completed
     * @throws DukeException If given index does not exist
     */
    public void completeTask(Ui ui, Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("This task number does not exist.");
        }
        Task t = taskList.get(taskNumber - 1);
        ui.print("This task is marked as completed:");
        ui.print(t.toString());
        t.markAsDone();
        storage.updateTasks(taskList);
    }

    /**
     * Deletes a task in the ArrayList
     * @param ui User interface to reply user
     * @param storage To store updated task status in file
     * @param taskNumber Index of task to be deleted
     * @throws DukeException If given index does not exist
     */
    public void deleteTask(Ui ui, Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("This task number does not exist.");
        }
        Task t = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        ui.print("This task has been deleted:");
        ui.print(t.toString());
        storage.updateTasks(taskList);
    }
}
