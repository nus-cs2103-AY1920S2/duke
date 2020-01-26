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
        echo(new String[]{input});
    }

    public static void echo(String[] inputs) {
        ProgramOutputHandler.printLineSeperator();
        for (int i = 0; i < inputs.length; ++i) {
            ProgramOutputHandler.printWithIndentation(inputs[i]);
        }
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
        String[] inputs = sc.nextLine().split(" ", 2);
        String command = inputs[0];
        String args = inputs.length > 1 ? inputs[1] : "";

        switch (command) {
        case "bye":
            printGoodbyeMessage();
            System.exit(0);
            break;

        case "list":
            listTasks();
            break;

        case "todo":
            // Add new todo
            Task newTodo = new Todo(args);
            tasks.add(newTodo);
            echo(new String[]{"New todo added:", "\t" + newTodo.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "deadline":
            // Add new deadline
            String[] splitByInputTime = args.split(" /by ");
            Task newDeadline = new Deadline(splitByInputTime[0], splitByInputTime[1]);
            tasks.add(newDeadline);
            echo(new String[]{"New deadline added:", "\t" + newDeadline.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "event":
            // Add new deadline
            String[] splitByEventTime = args.split(" /at ");
            Task newEvent = new Event(splitByEventTime[0], splitByEventTime[1]);
            tasks.add(newEvent);
            echo(new String[]{"New event added:", "\t" + newEvent.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "done":
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= tasks.size()) {
                // TODO: Handle exception or throw error message
                return;
            }

            tasks.get(index).setIsDone(true);
            listTasks();
            break;

        default:
            echo(command + " is not a recognized command. Please try again.");
            break;
        }
    }
}
