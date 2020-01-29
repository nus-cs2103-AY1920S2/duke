import java.util.ArrayList;

public class TaskList {


    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {

    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }


    public void remove(int toDelete) {
        tasks.remove(toDelete);
    }
}
