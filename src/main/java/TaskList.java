import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }
}
