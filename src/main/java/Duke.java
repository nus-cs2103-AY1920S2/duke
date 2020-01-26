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
            try {
                processInput(scanner);
            } catch (DukeException e) {
                echo(e.getMessage());
            }
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

    public static void processInput(Scanner sc) throws DukeException {
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
            if (args.equals(""))
                throw new DukeException("The description of a todo cannot be empty");

            // Add new todo
            Task newTodo = new Todo(args);
            tasks.add(newTodo);
            echo(new String[]{"New todo added:", "\t" + newTodo.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "deadline":
            if (args.equals(""))
                throw new DukeException("The description of a deadline cannot be empty");

            // Add new deadline
            String[] splitByInputTime = args.split(" /by ");
            Task newDeadline = new Deadline(splitByInputTime[0], splitByInputTime[1]);
            tasks.add(newDeadline);
            echo(new String[]{"New deadline added:", "\t" + newDeadline.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "event":
            if (args.equals(""))
                throw new DukeException("The description of a deadline cannot be empty");

            // Add new deadline
            String[] splitByEventTime = args.split(" /at ");
            Task newEvent = new Event(splitByEventTime[0], splitByEventTime[1]);
            tasks.add(newEvent);
            echo(new String[]{"New event added:", "\t" + newEvent.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "delete":
            if (args.equals(""))
                throw new DukeException("Missing task number for the command 'delete'.");

            int index = Integer.parseInt(args) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new DukeException("The input task number is invalid.");
            }

            Task task = tasks.remove(index);
            echo(new String[]{"You have removed the following task:", "\t" + task.toString(), "You now have " + tasks.size() + " tasks in the list"});
            break;

        case "done":
            if (args.equals(""))
                throw new DukeException("Missing task number for the command 'done'.");

            int deleteIndex = Integer.parseInt(args) - 1;

            if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                throw new DukeException("The input task number is invalid.");
            }

            tasks.get(deleteIndex).setIsDone(true);
            listTasks();
            break;

        default:
            throw new DukeException(command + " is not a recognized command. Please try again.");
        }
    }
}
