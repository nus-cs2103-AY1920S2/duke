import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public void doTask(int index) {
        this.tasks.get(index).doTask();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
