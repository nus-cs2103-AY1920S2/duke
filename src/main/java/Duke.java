import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    protected static Scanner scanner;
    protected static List<Task> tasks;

    public static void main(String[] args) {
        printWelcomeMessage();

        scanner = new Scanner(System.in);
        tasks = new ArrayList<Task>();

        while (true) {
            processInput(scanner);
        }
    }

    public static void printWelcomeMessage() {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation("Hello! I'm Duck");
        ProgramOutputHandler.printWithIndentation("What can I do for you?");
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    public static void printGoodbyeMessage() {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation("Bye. Hope to see you again soon!");
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    public static void echo(String input) {
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.printWithIndentation(input);
        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    private static void listTasks() {
        ProgramOutputHandler.printLineSeperator();

        for (int i = 0; i < tasks.size(); ++i) {
            Task t = tasks.get(i);
            ProgramOutputHandler.printWithIndentation((i + 1) + "." + t);
        }

        ProgramOutputHandler.printLineSeperator();
        ProgramOutputHandler.nextLine();
    }

    public static void processInput(Scanner sc) {
        String wholeInput = sc.nextLine();
        String[] inputs = wholeInput.split("\\s+"); // Regex \\s selects a space and + selects multiple

        switch (inputs[0]) {
        case "bye":
            printGoodbyeMessage();
            System.exit(0);
            break;
        case "list":
            listTasks();
            break;
        case "done":
            assert (inputs.length >= 2);
            int index = Integer.parseInt(inputs[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                // TODO: Handle exception or throw error message
                return;
            }

            tasks.get(index).setIsDone(true);
            listTasks();
            break;
        default:
            // Add new task
            Task newTask = new Task(wholeInput);
            tasks.add(newTask);
            echo("added: " + wholeInput);
            break;
        }
    }
}
