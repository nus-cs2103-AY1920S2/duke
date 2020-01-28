import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    private ArrayList<Task> arrTasks;
    private HashMap<String, String> taskToRegex;

    public TaskList() {
        this.arrTasks = new ArrayList<>();
        this.initRegexMap();
    }
    public TaskList(ArrayList<Task> arrTasks) {
        this.arrTasks = arrTasks;
        this.initRegexMap();
    }

    private void initRegexMap() {
        this.taskToRegex = new HashMap<>();
        this.taskToRegex.put("todo", "\n");
        this.taskToRegex.put("deadline", " /by ");
        this.taskToRegex.put("event", " /at ");
    }

    public ArrayList<Task> getTasks() {
        return this.arrTasks;
    }

    public int getNumTasks() {
        return this.arrTasks.size();
    }

    public void addTask(Task t) {
        this.arrTasks.add(t);
    }

    public Task removeTask(int i) {
        Task t = this.arrTasks.get(i);
        this.arrTasks.remove(i);
        return t;
    }

    public Task markTaskAsDone(int i) {
        Task t = this.arrTasks.get(i);
        t.setDone();
        return t;
    }
}
