import java.util.LinkedList;

public class Tracker {
    private LinkedList<Task> list;
    private int totalTasks;

    public Tracker() {
        this.list = new LinkedList();
        this.totalTasks = 0;
    }

    public void add(Task task) {
        this.totalTasks++;
        this.list.add(task);
    }

    public void markDone(int index) {
        Task task = this.list.get(index);
        task.setDone();
        System.out.println("  " + task);
    }

    public void delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        this.totalTasks--;
        System.out.println("  " + task);
    }

    public LinkedList<Task> showList() {
        return this.list;
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }
}
