import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public String addTask(Task task) {
        tasks.add(task);
        return String.format("Added: '%s'", task.getDescription());
    }

    public String removeTask(int index) {
        Task task = tasks.remove(index);
        return String.format("Removed: '%s'", task.getDescription());
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks so far";
        }
        StringBuilder output = new StringBuilder("Tasks so far:");
        ListIterator<Task> iterator = tasks.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            Task task = iterator.next();
            output.append(String.format("\n%d.%s", (index + 1), task));
        }
        return output.toString();
    }
}