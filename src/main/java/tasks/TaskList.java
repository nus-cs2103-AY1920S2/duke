package tasks;

import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores all the tasks input by the user.
 */
public class TaskList {
    /** List to store tasks. */
    private ArrayList<Task> list;
    /** Class of formatted user interactions. */
    private Ui ui;

    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return Size of list.
     */
    public int size() {
        return this.list.size();
    }

    /** Adds new task into the list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
        ui.printNewTask(t, this.size());
    }

    /**
     * Saves task into the save file.
     *
     * @param t Task to be saved.
     */
    public void save(Task t) {
        this.list.add(t);
    }

    /**
     * Deletes tasks from the list.
     *
     * @param index Index of task to be deleted in the list.
     */
    public void delete(int index) {
        Task t = this.list.get(index);
        this.list.remove(index);
        ui.printDelete(t, this.size());
    }

    /**
     * Marks index task as done.
     *
     * @param index Index of task to be marked as done.
     */
    public void markAsDone(int index) {
        Task t = this.list.get(index);
        t.markAsDone();
        ui.printDone(t);
    }

    /**
     * Concatenate all tasks into format used in save file.
     *
     * @return Save format of tasks in a string for easier back-conversion.
     */
    public String toSaveFormat() {
        String text = "";
        for (Task t : this.list) {
            text += t.toSaveFormat() + "\n";
        }
        return text;
    }

    /**
     * Searches the list for tasks with corresponding input date.
     * Prints out tasks with input date.
     *
     * @param date Date use for searching corresponding tasks.
     */
    public void searchDateTask(LocalDate date) {
        ArrayList<Task> dateTasks = new ArrayList<>();

        for (Task t : this.list) {
            if (t instanceof DateTask) {
                if (date.equals(((DateTask) t).getDate())) {
                    dateTasks.add(t);
                }
            }
        }
        ui.printList(dateTasks);
    }

    /**
     * Prints all tasks in the list.
     */
    public void print() {
        ui.printList(this.list);
    }

    /**
     * Returns indexed task.
     *
     * @param index Index of task.
     * @return Task with corresponding index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Searches the list for tasks with description containing the search word.
     *
     * @param searchWord Word to search for.
     * @return List of tasks with description containing the word.
     */
    public ArrayList<Task> search(String searchWord) {
        ArrayList<Task> searchList = new ArrayList<>();
        for (Task t : this.list) {
            if (t.getDescription().contains(searchWord)) {
                searchList.add(t);
            }
        }
        return searchList;
    }

    @Override
    public String toString() {
        String s = "";
        for (Task t : this.list) {
            s += t.toString() + "\n";
        }
        return s;
    }
}
