import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class Duke {
    private ArrayList<Task> tasks;

    public void listTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d. %s\n", i, tasks.get(i - 1));
        }
    }

    public void addTask(String task) {
        Task new_task = new Task(task);
        tasks.add(new_task);
        System.out.printf("added: %s\n", task);
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void processCommand(String command) {
        String[] splitted_arguments = command.split(" ");
        if (splitted_arguments[0].equals("list")) {
            listTasks();
        } else if (splitted_arguments[0].equals("done")) {
            Task task = getTask(Integer.parseInt(splitted_arguments[1]));
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.printf("   %s\n", task.toString());
        } else {
            addTask(command);
        }
    }
}
