package duke.tasks;

import java.util.List;
import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void add(List<Task> moreTasks) {
        tasks.addAll(moreTasks);
    }

    public List<Task> search(String keyword) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            // Case insensitive check
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }

    public Task get(int taskNo) {
        return tasks.get(taskNo - 1);
    }
    
    public Task remove(int taskNo) {
        return tasks.remove(taskNo - 1);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}