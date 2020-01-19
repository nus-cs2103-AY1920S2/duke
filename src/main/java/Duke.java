import java.util.ArrayList;

public class Duke {
    private ArrayList<String> tasks;

    public void listTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d. %s\n", i, tasks.get(i - 1));
        }
    }

    public void addTask(String task) {
        tasks.add(task);
        System.out.printf("added: %s\n", task);
    }

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void processCommand(String command) {
        if (command.equals("list")) {
            listTasks();
        } else {
            addTask(command);
        }
    }
}
