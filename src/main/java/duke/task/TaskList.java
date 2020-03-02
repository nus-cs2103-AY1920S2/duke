package duke.task;

import duke.ui.Ui;
import duke.exception.OutOfRangeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the Task at the index.
     * @param index the task index
     * @return the Task at the specified index
     * @throws OutOfRangeException If the provided index is out of range
     */
    public Task getTask(int index) throws OutOfRangeException {
        if (isValidTask(index)) {
            return tasks.get(index);
        } else {
            throw new OutOfRangeException();
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the Task at the specified index as done.
     * @param index The index of the Task
     * @throws OutOfRangeException If the provided index is out of range
     */
    public void markTaskAsDone(int index) throws OutOfRangeException {
        assert tasks.size() > 0 : "TaskList should not be empty.";
        if (isValidTask(index)) {
            tasks.get(index).markAsDone();
        } else {
            throw new OutOfRangeException();
        }
    }

    /**
     * Deletes the Task at the specified index.
     * @param index The index of the Task
     * @throws OutOfRangeException If the provided index is out of range
     */
    public void deleteTask(int index) throws OutOfRangeException {
        if (isValidTask(index)) {
            this.tasks.remove(index);
        } else {
            throw new OutOfRangeException();
        }
    }

    /**
     * Displays the tasks currently in Duke's database.
     */
    public void displayTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                result.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            } else {
                result.append(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }
        Ui.printMessage(result.toString());
    }

    /**
     * Finds tasks that contains the specified input.
     * @param searchTerm The term to search for
     * @return Tasks that contain the search term in their description
     */
    public String findTask(String searchTerm) {
        int i = 0;
        StringBuilder result = new StringBuilder("Here's the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                result.append(String.format("\n\t%d. %s", ++i, task.toString()));
            }
        }
        if (i == 0) {
            return "No results matching your search was found!";
        }
        return result.toString();
    }

    /**
     * Checks if the specified index corresponds to a valid Task.
     * @param index The index of the Task
     * @return True if the task exists, false otherwise
     * @throws OutOfRangeException If the provided index is out of range
     */
    public boolean isValidTask(int index) throws OutOfRangeException {
        if (index < 0 || index >= tasks.size()) {
            throw new OutOfRangeException();
        } else {
            return true;
        }
    }
}