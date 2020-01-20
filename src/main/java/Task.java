import java.util.ArrayList;

public class Task {

    static int totalTask = 0;
    static ArrayList<Task> taskArr = new ArrayList<Task>();

    protected int index;
    protected boolean isDone;
    protected String taskDescription;

    public Task(int index, String taskDescription) {
        this.index = index;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public static void list() {
        String header = "____________________________________________________________\n "
                + "Here are the tasks in your list: ";
        System.out.println(header);

        for (int i = 0; i < taskArr.size(); i++) {
            System.out.println((i + 1) + "." + taskArr.get(i));
        }

        String foot = "____________________________________________________________ ";
        System.out.println(foot);
    }

    public static void addTask(String command) {
        Task.totalTask++;
        taskArr.add(new Task(Task.totalTask, command));
        String text = "____________________________________________________________ \n"
                + "added: " + command + "\n"
                + "____________________________________________________________ \n";
        System.out.println(text);
    }

    public static Task getTask(int index) {
        return taskArr.get(index - 1);
    }

    public void markDone() {
        isDone = true;
        String header = "____________________________________________________________ \n";
        String action = "Nice! I've marked this task as done:\n";
        String task = this.toString();
        String footer = "____________________________________________________________ \n";
        System.out.println(header + action + task + footer);
    }

    @Override
    public String toString() {

        String icon = null;

        if (isDone) {
            icon = "DONE";
        } else {
            icon = "NOT DONE";
        }
        return "[" + icon + "]" + " " + taskDescription + "\n";
    }
}
