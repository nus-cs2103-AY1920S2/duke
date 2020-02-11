/**
 * This serves as a wrapper to store an ArrayList of existing tasks.
 * This is also able to edit tasks in the ArrayList
 */
package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;

import duke.exceptions.MissingKeywordException;
import duke.exceptions.UnknownTaskException;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class TaskList {

    private ArrayList<Task> tasks;
    private TreeSet<Task> schedule;
    /**
     * Creates a TaskList object from an existing list of tasks
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.schedule = new TreeSet<>();

        for (Task task : tasks) {
            if (task instanceof Deadline || task instanceof Event) {
                schedule.add(task);
            }
        }
    }

    /**
     * Creates a TaskList object with a new list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.schedule = new TreeSet<>();
    }

    /**
     * Adds a task to the ArrayList
     * @param task Task to be added to ArrayList
     */
    public void addTask(Task task) {
        tasks.add(task);
        if (task instanceof Deadline || task instanceof Event) {
            schedule.add(task);
        }
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
     * Prints to console the schedule specified
     */
    public String printSchedule(LocalDate date) {
        StringBuilder output = new StringBuilder();
        if (size() == 0) {
            output.append("Your list of tasks is currently empty.\n");
        } else {
            output.append("This is your schedule on " + date + ":\n");
            int taskIdx = 1;
            for (Task task : schedule) {
                if (task.getDate().toString().equals(date.toString())) {
                    output.append(taskIdx + ". ");
                    output.append(task.toString() + "\n");
                    taskIdx++;
                }
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
        schedule.remove(task);

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
