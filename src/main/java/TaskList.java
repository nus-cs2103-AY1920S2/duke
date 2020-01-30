import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public void newTodo(String task) {
        tasks.add(new Todo(task));
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void newDeadline(String task, LocalDateTime time) {
        tasks.add(new Deadline(task, time));
    }

    public void newEvent(String task, LocalDateTime time) {
        tasks.add(new Event(task, time));
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void remove(Task targetedTask) {
        this.tasks.remove(targetedTask);
    }
}
