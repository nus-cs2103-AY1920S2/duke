package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores all the tasks input by the user.
 */
public class TaskList {
    /** List to store tasks. */
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
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
    }

    /**
     * Marks index task as done.
     *
     * @param index Index of task to be marked as done.
     */
    public void markAsDone(int index) {
        Task t = this.list.get(index);
        t.markAsDone();
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
    public TaskList searchDateTask(LocalDate date) {
        TaskList dateTasks = new TaskList();

        for (Task t : this.list) {
            if (t instanceof DateTask) {
                if (date.equals(((DateTask) t).getDate())) {
                    dateTasks.add(t);
                }
            }
        }

        return dateTasks;
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
     * @param keyword Word to search for.
     * @return List of tasks with description containing the word.
     */
    public TaskList search(String keyword) {
        TaskList successList = new TaskList();
        for (Task t : this.list) {
            if (t.getDescription().contains(keyword)) {
                successList.add(t);
            }
        }
        return successList;
    }

    @Override
    public String toString() {
        String s = "";
        for (Task t : this.list) {
            s += t.toString() + "\n";
        }
        return s;
    }

    /**
     * Stringify the task list into an ordered numerical list.
     *
     * @return Stringified numerical list.
     */
    public String orderedToString() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += (i + 1) + ")\t" + list.get(i).toString() + "\n";
        }
        return s.strip();
    }

    /**
     * Finds and returns a TaskList of all tasks tagged with given tag.
     * @param tag Tag to be filtered.
     * @return List of all tagged tasks/
     */
    public TaskList findTag(String tag) {
        TaskList tasksWithTag = new TaskList();
        for (Task t : this.list) {
            if (t.containTag(tag)) {
                tasksWithTag.add(t);
            }
        }
        return tasksWithTag;
    }
}
