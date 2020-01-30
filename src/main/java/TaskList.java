import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task completeTask(int index) {
        Task task = tasks.get(index);
        task.tick();
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += i + "."
                    + tasks.get(i) + "\n";
        }

        return result;
    }
}
