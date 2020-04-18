package duke;
import duke.exceptions.InvalidIndexException;

import java.util.List;
import java.util.ArrayList;

/**
 * Stores a temporary list of current tasks of the user. TaskList is able to perform various
 * operations, such as adding, updating, deleting, and even show all of them.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor. Starts with an empty list, if no previous tasks is left by
     * the user.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overriding constructor. If the user has any leftover incomplete tasks, includes them
     * for every operations later.
     * @param tasks The previous incomplete list of tasks by the user.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the current list.
     * @param task The current task that needs to be added into the list.
     * @return An output specifying how many tasks the user has, after the adding.
     */
    public String addTask (Task task) {
        if (hasDuplicateTask(task)) {
            return "This task has been added!";
        }
        this.tasks.add(task);
        String res = "Got it. I've added this task:" + "\n";
        res += task + "\n";
        res += "Now you have " + this.tasks.size()
                + (this.tasks.size() > 1 ? " tasks" : " task") + " in the list";
        return res;
    }

    private boolean hasDuplicateTask(Task task) {
        for (Task added : this.tasks) {
            if (added.getDescriptionWithoutIcon().equals(task.getDescriptionWithoutIcon())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicateDeadline(Deadline deadline) {
        for (Task added : this.tasks) {
            if (added instanceof Deadline) {
                if (added.getDescriptionWithoutIcon().equals(deadline.getDescriptionWithoutIcon())
                        && ((Deadline) added).getDeadline().equals(deadline.getDeadline())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasDuplicateEvent(Event event) {
        for (Task added : this.tasks) {
            if (added instanceof Event) {
                if (added.getDescriptionWithoutIcon().equals(event.getDescriptionWithoutIcon())
                        && ((Event) added).getTime().equals(event.getTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a deadline to the current list.
     * @param deadline The deadline that needs to be added into the list.
     * @return An output specifying how many tasks the user has, after the adding.
     */
    public String addDeadline(Deadline deadline) {
        if (hasDuplicateDeadline(deadline)) {
            return "This deadline is already added!";
        }
        this.tasks.add(deadline);
        String res = "Got it. I've added this deadline:" + "\n";
        res += deadline + "\n";
        res += "Now you have " + this.tasks.size()
                + (this.tasks.size() > 1 ? " tasks" : " task") + " in the list";
        return res;
    }

    /**
     * Adds an event to the current list.
     * @param event The event that needs to be added into the list.
     * @return An output specifying how many tasks the user has, after the adding.
     */
    public String addEvent(Event event) {
        if (hasDuplicateEvent(event)) {
            return "This event is already added!";
        }
        this.tasks.add(event);
        String res = "Got it. I've added this deadline:" + "\n";
        res += event + "\n";
        res += "Now you have " + this.tasks.size()
                + (this.tasks.size() > 1 ? " tasks" : " task") + " in the list";
        return res;
    }

    /**
     * Shows all the tasks, including those that are done or incomplete, to the user.
     * @return A string that includes every task.
     */
    public String list()  {
        if (this.tasks.size() == 0) {
            return "You have no pending tasks!";
        }
        String res = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            res += (i + 1) + "." + this.tasks.get(i);
            if (i != this.tasks.size() - 1) {
                res += "\n";
            }
        }
        return res;
    }

    public String clear() {
        this.tasks.clear();
        return "All tasks have been cleared!";
    }

    /**
     * Finds the task with a keyword requested by the user.
     * @param key The keyword the user wants to search with.
     * @return A string that includes all the tasks with the entered keyword.
     */
    public String find(String key) {
        String res = "Here are the matching tasks in your list:" + "\n";
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescriptionWithoutIcon().contains(key)) {
                filteredTasks.add(task);
            }
        }
        for (int i = 0; i < filteredTasks.size(); i++) {
            res += (i + 1) + "." + filteredTasks.get(i) + "\n";
        }
        return res;
    }

    /**
     * Marks a certain task as done upon completion.
     * @param idx The index of the task that needs to be marked done.
     * @return A string that shows the status of the updated task.
     * @throws InvalidIndexException incorrect index, i.e. a negative number.
     */
    public String done(int idx) throws InvalidIndexException {
        String res = "";
        if (idx < 0 || idx >= tasks.size()) {
            String message = "OOPS! Invalid task number. You only have " + this.tasks.size()
                    + (this.tasks.size() > 1 ? " tasks" : " task") + " in the list";
            throw new InvalidIndexException(message);
        }
        Task curr = this.tasks.get(idx);
        curr.setDone();
        res += "Nice! I've marked this task as done:" + "\n";
        res += curr;
        return res;
    }

    /**
     * Removes a task from the list as requested by the user.
     * @param idx The index of the task that needs to be removed.
     * @return A string denotes the number of tasks the user have after the deletion.
     * @throws InvalidIndexException incorrect index, i.e. a large number when tasks are only a few.
     */
    public String delete(int idx) throws InvalidIndexException {
        String res = "";
        if (idx < 0 || idx >= tasks.size()) {
            String message = "OOPS! Invalid task number. You only have " + this.tasks.size()
                    + (this.tasks.size() > 1 ? " tasks" : " task") + " in the list";
            throw new InvalidIndexException(message);
        }
        Task toDelete = this.tasks.get(idx);
        this.tasks.remove(idx);
        res += ("Noted. I've removed this task: \n");
        res += (toDelete + "\n");
        res += ("Now you have " + this.tasks.size()
                    + " " + (this.tasks.size() > 1 ? "tasks" : "task") + " in the list");
        return res;
    }

    /**
     * A getter method to access the list by the TaskList class.
     * @return A list of all the tasks.
     */
    public List<Task> getAllTasks() {
        return this.tasks;
    }
}