import duke.task.*;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public String showList() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("");
            sb.append("Here is your list of tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                int index = i + 1;
                sb.append("\n" + index + "." + task.toString());
            }
            return sb.toString();
        }
    }
}