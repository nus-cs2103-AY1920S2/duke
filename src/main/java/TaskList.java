import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
        this.ui = new Ui();
    }

    private static void addTask(Task task) {
        tasks.add(task);
        ui.add(task, tasks);
    }

    private static void doneTask(int n) {
        tasks.get(n-1).setDone();
        ui.done(n, tasks);
    }

    private static void deleteTask(int n) {
        ui.delete(n, tasks);
        tasks.remove(n-1);
        ui.count(tasks);
    }

    private static void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            if (instructions.get(i) != null) {
                System.out.println((i + 1) + ". " + instructions.get(i).toString());
            }
        }
    }
}