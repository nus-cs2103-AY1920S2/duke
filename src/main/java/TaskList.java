import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this(new ArrayList<Task>());
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public ArrayList<Task> getTaskState() {
        return tasks;
    }
    
    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public Task removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }
    
    public void markTaskAsDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.markAsDone();
    }
    
    public int size() {
        return tasks.size();
    }
}
