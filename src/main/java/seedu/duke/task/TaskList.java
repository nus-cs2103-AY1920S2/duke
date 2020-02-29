package seedu.duke.task;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains the task list and operations to add, delete, and change the list.
 */
public class TaskList {
    protected List<Task> tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for TaskList.
     *
     * @param tasks The list of tasks.
     * @param storage The hard disk for storage of data.
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        ui = new Ui();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);
    }

    public void printList() {
        ui.printList(tasks);
    }

    /**
     * Marks the task as done by changing the done status from "N" to "Y".
     * It also updates the done status in the hard disk storage list accordingly.
     *
     * @param index The index number of the task that is marked as done.
     * @throws IOException If an input or output exception occurred.
     */
    public void markTaskAsDone(int index) throws IOException {
        Task task = tasks.get(index - 1);
        if (task.isDone()) {
            ui.printTaskAlrDone();
        } else {
            task.markAsDone();
            assert task.isDone();
            storage.changeToStorage(index);
            ui.printTaskDone();
        }
        ui.print(task.toString());
    }

    /**
     * Deletes the task from the task list and the hard disk storage list accordingly.
     *
     * @param index The index number of the task that is being deleted.
     * @throws IOException If an input or output exception occurred.
     */
    public void deleteTask(int index) throws IOException {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.printRemoveTask();
        ui.print(task.toString());
        ui.printNumTask(tasks);
        storage.deleteInStorage(index);
    }

    /**
     * Finds task(s) which contains a substring given by the user.
     *
     * @param desc A substring of a task that user wants to find.
     */
    public void findTask(String desc) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(desc)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.size() == 0) {
            ui.printNoFoundTask();
        } else {
            assert foundTasks.size() > 0 : foundTasks.size();
            ui.printFoundTask();
            ui.printList(foundTasks);
        }
    }
}
