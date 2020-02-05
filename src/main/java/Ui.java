import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    private Scanner scanner;
    private String indent;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.indent = "      ";
    }

    public void loadMachinePrompt() {
        System.out.print("Dude: ");
    }

    public void loadUserPrompt() {
        System.out.print("dude: ");
    }

    public void greet() {
        loadMachinePrompt();
        System.out.print("Hi dude! I'm your Dude\n"
                + indent + "What do you want?\n");
        loadUserPrompt();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLoadingError(String message) {
        loadMachinePrompt();
        System.out.println("\n" + indent + "Loading error: " + message);
        loadUserPrompt();
    }

    public void showCommandError(String message) {
        loadMachinePrompt();
        System.out.println("\n" + indent + "Command error: " + message);
        loadUserPrompt();
    }

    public void showAddingError(String message) {
        loadMachinePrompt();
        System.out.println("\n" + indent + "Adding error: " + message);
        loadUserPrompt();
    }

    public void showDeletingError(String message) {
        loadMachinePrompt();
        System.out.println("\n" + indent + "Deleting error: " + message);
        loadUserPrompt();
    }

    public void showMarkingAsDoneError(String message) {
        loadMachinePrompt();
        System.out.println("\n" + indent + "Marking as done error: " + message);
        loadUserPrompt();
    }

    public void printAddingMessage(TaskList tasks, Task task) {
        loadMachinePrompt();
        System.out.println("Got it dude! I've added this task:" + "\n"
                + indent + task);
        printTaskListSize(tasks);
        loadUserPrompt();
    }

    public void printDoneMessage(TaskList tasks, Task task) {
        loadMachinePrompt();
        System.out.println("Got it dude! I've marked this task as done:" + "\n"
                + indent + task);
        loadUserPrompt();
    }

    public void printDeletingMessage(TaskList tasks, Task task) {
        loadMachinePrompt();
        System.out.println("Got it dude! I've deleted this task:" + "\n"
                + indent + task);
        printTaskListSize(tasks);
        loadUserPrompt();
    }

    public void printTaskListSize(TaskList tasks) {
        int size = tasks.getListSize();
        System.out.println(indent + "Now you have " + size + " task(s) in the list.");
    }

    public void printEmptyListMessage() {
        loadMachinePrompt();
        System.out.println("Your list is currently empty dude.");
        loadUserPrompt();
    }

    public void printTaskMessage() {
        System.out.println("Here's your list of tasks dude:");
    }

    public void printFoundTaskMessage() {
        System.out.println("Okay dude here are what I found:");
    }

    public void printTask(int i, Task task) {
        System.out.println(indent + i + ". " + task);
    }

    public void exit() {
        loadMachinePrompt();
        System.out.println("Okay see ya dude!");
    }
}
