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

            // obtain String command and split it to find the intention of the user (first word)
            String command = io.nextLine();
            String[] commandWords = command.split("\\s");

            switch (commandWords[0]) {

            case "bye":
                return;

            case "list":
                printAllTasks();
                break;

            case "done":
                // user inputs will be "done _____"
                // Take only the very next token which must be an integer
                if (commandWords.length > 1) {
                    Task currentTaskDone = allTasks.get(Integer.valueOf(commandWords[1]) - 1);
                    doTask(currentTaskDone);
                } else {
                    print("Seems like you are kinda tired. Please remember to define a Task Number!"
                        + "Or, you could also take a break. :)");
                }
                break;

            default:
                // This is the only part where we create new tasks
                // Add task, then announce this addition
                Task currentTaskDefault = new Task(command);
                addTaskToStored(currentTaskDefault);

            }

        }
    }

    // Adds the Task to store in the ArrayList, and prints added message
    private void addTaskToStored(Task t) {
        allTasks.add(t);
        t.taskAddedMessage();
    }

    // Mark task complete and prints completion message
    private void doTask(Task t) {
        printTaskComplete(t);
        t.doTask();
    }

    // Prints all tasks, their number order, and their completion
    private void printAllTasks() {
        printLine();
        for (int i = 0; i < allTasks.size(); i++) {
            printTaskFromStored(i);
        }
        printLine();
    }

    // for utility
    private void printTaskComplete(Task t) {
        printLine();
        if (t.getIsDone()) {
            print("That's already done, try another. Or did you make a careless mistake? XD");
        } else {
            print("Nice! The following task has been marked completed:\n"
                    + "===> [\u2713] " + t + " <===");
        }
        printLine();
    }

    // for utility
    private void printTaskFromStored(int i) {
        String tickOrCross = allTasks.get(i).obtainStatusIcon();
        print(String.valueOf(i+1) + ". [" + tickOrCross + "] " + allTasks.get(i));
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
