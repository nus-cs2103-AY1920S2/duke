import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i));
        }
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task remove(int taskNumber) {
        return tasks.remove(taskNumber);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
