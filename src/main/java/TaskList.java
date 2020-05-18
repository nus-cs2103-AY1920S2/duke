import java.util.ArrayList;

/**
 * <h1> TaskList </h1>
 * TaskList keeps track of the current tasks involved in the program
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void markDone(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public ArrayList<String> search(String searchTerm) {
        ArrayList<String> taskString = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            if (curr.contains(searchTerm)) {
                taskString.add(String.format("%d.%s", i + 1, this.tasks.get(i)));
            }
        }
        return taskString;
    }
}
