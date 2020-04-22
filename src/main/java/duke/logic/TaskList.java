package duke.logic;

import duke.commons.Deadline;
import duke.commons.Event;
import duke.commons.Task;
import duke.commons.Todo;
import duke.commons.exceptions.DuplicateTaskException;
import duke.commons.exceptions.InvalidIndexException;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a manager of <code>Task</code> objects that contains a task list,
 * and could add/delete a <code>Task</code> from the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the <code>Task</code> object at the specified position in the task list.
     *
     * @param index index of the <code>Task</code> object to return.
     * @return the <code>Task</code> object at the specified position in the task list.
     * @throws InvalidIndexException If the index is out of bound.
     */
    public Task getTask(int index) throws InvalidIndexException {
        try {
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Sorry dude but this index is nowhere to be found.");
        }
    }

    /**
     * Returns the list of all <code>Task</code> objects.
     *
     * @return the list of all <code>Task</code> objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the number of <code>Task</code> objects in the task list.
     *
     * @return the number of <code>Task</code> objects in the task list.
     */
    public int getListSize() {
        return this.taskList.size();
    }

    /**
     * Returns a <code>String</code> representing the <code>Task</code> objects in the task list.
     *
     * @param ui the <code>Ui</code> object of the program.
     * @return a <code>String</code> representing the <code>Task</code> objects in the task list,
     *     or a <code>String</code> stating that the task list is empty.
     */
    public String printTaskList(Ui ui) {
        String output;
        if (this.taskList.isEmpty()) {
            output = ui.printEmptyListMessage();
        } else {
            output = ui.printTaskMessage();
            for (int i = 1; i <= this.taskList.size(); i++) {
                Task task = this.taskList.get(i - 1);
                output += "\n";
                output += ui.printTask(i, task);
            }
        }
        return output;
    }

    /**
     * Adds a <code>Task</code> object to the task list and returns that <code>Task</code> object.
     *
     * @param commandWord Command word provided by user.
     * @param commands array of <code>String</code> tokens from the input <code>String</code> provided by user.
     * @return a <code>Task</code> object just added to the task list.
     * @throws DuplicateTaskException If the <code>Task</code> object already existed.
     */
    public Task addTask(String commandWord, String[] commands) throws DuplicateTaskException {
        Task task = null;
        String description;
        String[] descriptions;
        switch (commandWord) {
        case "todo":
            description = commands[1];
            detectDuplicate(commandWord, description);
            task = new Todo(commandWord, false, description);
            break;
        case "event":
            descriptions = commands[1].split(" /at ");
            detectDuplicate(commandWord, descriptions[0]);
            task = new Event(commandWord, false, descriptions[0], descriptions[1]);
            break;
        case "deadline":
            descriptions = commands[1].split(" /by ");
            detectDuplicate(commandWord, descriptions[0]);
            task = new Deadline(commandWord, false, descriptions[0], descriptions[1]);
            break;
        default:
            assert 1 == 0 : "default reached";
        }
        assert task != null : "null task";
        this.taskList.add(task);
        return task;
    }

    /**
     * Marks the <code>Task</code> object at the specified index as done.
     *
     * @param doneIndex index of the <code>Task</code> object to be marked as done.
     */
    public void markTaskAsDone(int doneIndex) {
        Task task = this.taskList.get(doneIndex);
        task.markAsDone();
    }

    /**
     * Removes the <code>Task</code> object at the specified index from the task list.
     *
     * @param deleteIndex index of the <code>Task</code> object to be removed.
     */
    public void deleteTask(int deleteIndex) {
        this.taskList.remove(deleteIndex);
    }

    /**
     * Checks whether a <code>AddCommand</code> is trying to add a <code>Task</code> object that already existed.
     *
     * @param command <code>String</code> representation of the type of <code>AddCommand</code> to be checked.
     * @param description <code>String</code> arguments of the <code>AddCommand</code> to be checked.
     * @throws DuplicateTaskException If the <code>Task</code> object to be added already existed.
     */
    public void detectDuplicate(String command, String description) throws DuplicateTaskException {
        for (int i = 1; i <= this.taskList.size(); i++) {
            Task task = this.taskList.get(i - 1);
            if (task.getType().equals(command) && task.getDescription().equals(description)) {
                throw new DuplicateTaskException("You already have this task dude!");
            }
        }
    }

    /**
     * Returns a <code>String</code> representing the <code>Task</code> objects that matches the keyword provided.
     *
     * @param ui <code>Ui</code> object of the program.
     * @param keyword keyword provided by the user.
     * @return a <code>String</code> representing the <code>Task</code> objects that matches the keyword provided,
     *     or a message stating that there is no <code>Task</code> object matching the keyword provided.
     */
    public String findTask(Ui ui, String keyword) {
        boolean isFound = false;
        String output = ui.printFoundTaskMessage();
        for (int i = 1; i <= this.taskList.size(); i++) {
            Task task = this.taskList.get(i - 1);
            if (task.getDescription().contains(keyword)) {
                isFound = true;
                output += "\n";
                output += ui.printTask(i, task);
            }
        }
        if (!isFound) {
            output = ui.printNotFoundTaskMessage();
        }
        return output;
    }
}
