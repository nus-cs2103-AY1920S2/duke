import java.util.ArrayList;

public class TaskList {
    private  ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getTaskNumber() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void removeTask(Task t) {
        this.tasks.remove(t);
    }
}
