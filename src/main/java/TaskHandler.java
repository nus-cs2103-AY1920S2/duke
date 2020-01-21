import java.util.Scanner;
import java.util.ArrayList;

public class TaskHandler {

    // for storing tasks/data
    private ArrayList<Task> allTasks = new ArrayList<>();
    // for utility
    private String horizontalLine = "____________________________________________________________";

    // Serves the user to do the bulk of the work
    public void serveUser() {

        // Scanner object to take in user input
        Scanner io = new Scanner(System.in);

        while (io.hasNextLine()) {

            String command = io.nextLine();

            switch (command) {

            case "bye":
                return;

            case "list":
                printAllTasks();
                break;

            default:
                // This is the only part where we create new tasks
                Task currentTask = new Task(command);
                addTaskToStored(currentTask);

            }

        }
    }

    // adds the Task to store in the ArrayList, and prints added message
    private void addTaskToStored(Task t) {
        allTasks.add(t);
        t.taskAddedMessage();
    }

    private void printAllTasks() {
        printLine();
        for (int i = 0; i < allTasks.size(); i++) {
            printTaskFromStored(i);
        }
        printLine();
    }

    // for utility
    private void printTaskFromStored(int i) {
        print(String.valueOf(i+1) + ". " + allTasks.get(i));
    }

    // for utility
    private void printLine() {
        print(horizontalLine);
    }

    // for utility
    private void print(String s) {
        System.out.println(s);
    }
}
