import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static int size() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void delete(int deletionNumber) {
        tasks.remove(deletionNumber - 1);
    }

    public void markDone(int number) {
        tasks.get(number - 1).markAsDone();
    }
}