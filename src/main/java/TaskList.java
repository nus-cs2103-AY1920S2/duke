import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addToTasks(Task task) {
        tasks.add(task);
    }

    public void deleteFromTasks(int taskNum) {
        tasks.remove(taskNum - 1);
    }

    public void setAsDone(int taskNum) {
        tasks.get(taskNum - 1).markAsDone();
    }

    public Task getTask(int taskNum) {
        return tasks.get(taskNum - 1);
    }

    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks have been added!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }
}
