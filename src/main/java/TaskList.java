import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int size;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = this.tasks.size();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        this.size++;
    }

    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        Task deletedTask = this.tasks.remove(taskNumber - 1); // need to minus 1 as 0-index based arraylist
        this.size--;
        return deletedTask;
    }

    public Task getTask(int taskNumber) throws IndexOutOfBoundsException {
        return this.tasks.get(taskNumber - 1);
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
