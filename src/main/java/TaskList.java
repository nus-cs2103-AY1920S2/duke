import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public ArrayList<Task> getTasks() {
        return this.tasksList;
    }

    public int getNumTasks() {
        return this.tasksList.size();
    }

    public void addTask(Task t) {
        this.tasksList.add(t);
    }

    public Task removeTask(int i) {
        Task t = this.tasksList.get(i);
        this.tasksList.remove(i);
        return t;
    }

    public Task markTaskAsDone(int index) {
        Task t = this.tasksList.get(index);
        t.markAsDone();
        return t;
    }

}
