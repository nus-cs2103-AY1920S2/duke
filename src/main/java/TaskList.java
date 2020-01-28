import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public void add(String taskType, String taskDescription) {
        Task temp = new Task("random");
        if (taskType.equals("deadline")) {
            try {
                temp = new Deadline(taskDescription.split("/", 2));
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.descriptionError();
            }
        } else if (taskType.equals("event")) {
            try {
                temp = new Event(taskDescription.split("/", 2));
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.descriptionError();
            }
        } else {
            temp = new ToDo(taskDescription);
            tasks.add(temp);
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + temp);
        System.out.println("\tNow you have " + tasks.size() + " tasks in your list.");
        System.out.println("\t____________________________________________________________");
    }

    public void list() {
        int size = tasks.size();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < size; ++i) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }

    public void done(int n) {
        if (n > tasks.size()) {
            System.out.println("\tThere is no such task");
        } else {
            System.out.println("\tNice! I've marked this task as done:");
            tasks.get(n - 1).markAsDone();
            System.out.println("\t" + tasks.get(n - 1));
        }
    }

    public void delete(int n) {
        if (n > tasks.size()) {
            System.out.println("\tThere is no such task");
        } else {
            System.out.println("\tNoted. I have removed this task:");
            System.out.println("\t" + tasks.get(n - 1));
            tasks.remove(n-1);
            System.out.println("\tYou now have " + tasks.size() + " tasks in the list.");
        }
    }

}
