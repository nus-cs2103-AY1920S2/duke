import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public void done(int index) {
        tasks.get(index).markAsDone();
    }

    @Override
    public String toString() {
        String toReturn = "\n";

        int listNo = 1;
        for (Task task : tasks) {
            toReturn += listNo + " ";
            toReturn += task + "\n";

            listNo++;
        }
        return toReturn;
    }
}
