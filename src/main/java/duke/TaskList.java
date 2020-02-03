package duke;

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

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) throws OutOfRangeException {
        this.tasks.remove(index);
    }

    public void displayTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                result.append(String.format("%d. %s\n\t", i + 1, tasks.get(i)));
            } else {
                result.append(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }
        Ui.printMessage(result.toString());
    }

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
}