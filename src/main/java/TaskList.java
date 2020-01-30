import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getNumTasks() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task removeTask(int i) {
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return t;
    }

    public Task markTaskAsDone(int i) {
        Task t = this.tasks.get(i);
        t.setDone();
        return t;
    }
}
