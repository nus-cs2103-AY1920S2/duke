package duke.task;

import duke.exception.InvalidCommandException;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setAsDone(int index) throws InvalidCommandException {
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException("     ☹ OOPS!!! I cannot set a "
                    + "non-existent task to be done.");
        }
        tasks.get(index - 1).markDone();
    }

    public Task deleteTask(int index) throws InvalidCommandException {
        if (index > tasks.size() || index <= 0) {
            throw new InvalidCommandException("     ☹ OOPS!!! I cannot delete a "
                    + "non-existent task.");
        }
        Task taskToDelete = tasks.remove(index - 1);
        return taskToDelete;
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Finds the list of tasks that contains the search phrase.
     *
     * @param searchPhrase the desired search phrase to search the list.
     * @return the string representation of the filtered list.
     */
    public String findTasksBySearchPhrase(String searchPhrase) {
        String relevantTasksRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(searchPhrase)) {
                relevantTasksRepresentation += String.format("     %d. %s\n", (i + 1), tasks.get(i));
            }
        }
        return relevantTasksRepresentation;
    }

    public List<Task> getList() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public String toString() {
        String listRepresentation = "";
        for (int i = 0; i < tasks.size(); i++) {
            listRepresentation += String.format("     %d. %s\n", (i + 1), tasks.get(i));
        }
        return listRepresentation;
    }
}
