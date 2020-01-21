import java.util.LinkedList;

public class Tracker {
    private LinkedList<Task> list;
    private int totalTasks;

    public Tracker() {
        this.list = new LinkedList();
        this.totalTasks = 0;
    }

    public void add(String text) {
        this.totalTasks++;
        this.list.add(new Task(text));
    }

    public LinkedList<Task> showList() {
        return this.list;
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }
}
