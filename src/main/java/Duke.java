import java.util.Scanner;

public class Duke {

    protected static Scanner scanner;
    protected static TaskList tasks;

    protected static final String OUTPUT_INDENTATION = "\t";
    protected static final String OUTPUT_HORIZONTAL_LINE = "\t________________________________________________________";

    public static void main(String[] args) {
        printWelcomeMessage();
        scanner = new Scanner(System.in);

        // Try to read tasks from disk
        tasks = new TaskList(Serializer.deserialize());

        while (true) {
            try {
                processInputs(scanner);
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    protected static void processInputs(Scanner sc) throws DukeException {
        String[] inputs = sc.nextLine().split(" ", 2);
        String command = inputs[0];
        String args = inputs.length > 1 ? inputs[1] : "";
        Task newTask = null;

        switch (command) {
        case "bye":
            // Fallthrough
        case "quit":
            // Fallthrough
        case "exit":
            // Fallthrough
        case "drop_module":
            // Fallthrough
        case "withdraw_from_uni":
            quit();
            break;

        case "list":
            printAllTasks();
            break;

        case "todo":
            DukeException.throwIf(!args.equals(""), "The description of a todo cannot be empty");
            addNewTask(new Todo(args));
            break;

        case "deadline":
            String[] splitByInputTime = args.split(" /by ");
            DukeException.throwIf(!args.equals(""), "The description of a deadline cannot be empty");
            DukeException.throwIf(splitByInputTime.length >= 2, "Input is missing a '/by' argument!");
            addNewTask(new Deadline(splitByInputTime[0], splitByInputTime[1]));
            break;

        case "event":
            String[] splitByEventTime = args.split(" /at ");
            DukeException.throwIf(!args.equals(""), "The description of a event cannot be empty");
            DukeException.throwIf(splitByEventTime.length >= 2, "Input is missing a '/at' argument!");
            addNewTask(new Event(splitByEventTime[0], splitByEventTime[1]));
            break;

        case "delete":
            if (args.equals("all")) {
                removeAllTasks();
                break;
            }

            int deletionIndex;
            try {
                deletionIndex = Integer.parseInt(args) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("The input index is missing or is not a number!");
            }
            DukeException.throwIf(deletionIndex < tasks.size(), "The input index is out of bounds!");
            DukeException.throwIf(deletionIndex >= 0, "The input index is out of bounds!");
            removeTaskAtIndex(deletionIndex);
            break;

        case "done":
            int targetIndex;
            try {
                targetIndex = Integer.parseInt(args) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("The input index is missing or is not a number!");
            }

            DukeException.throwIf(targetIndex < tasks.size(), "The input task number is out of bounds!");
            DukeException.throwIf(targetIndex >= 0, "The input task number is out of bounds!");
            tasks.get(targetIndex).setIsDone(true);
            printAllTasks();
            break;

        default:
            throw new DukeException(command + " is not a recognized command." +
                    "\nYou are advised to stop trying to break the system.");
        }
    }

    protected static void printWelcomeMessage() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_INDENTATION + "Hello");
        System.out.println(OUTPUT_INDENTATION + "What can I do for you?");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected static void printGoodbyeMessage() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_INDENTATION + "Bye. Hope to never see you again!");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected static void addNewTask(Task newTask) {
        tasks.addTask(newTask);
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_INDENTATION + "New task added: ");
        System.out.println(OUTPUT_INDENTATION + OUTPUT_INDENTATION + newTask.toString());
        System.out.println(OUTPUT_INDENTATION + String.format("You now have %d task(s) in the list.", tasks.size()));
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected static void removeTaskAtIndex(int index) {
        Task task = tasks.removeAtIndex(index);
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_INDENTATION + "You have removed the following task: ");
        System.out.println(OUTPUT_INDENTATION + OUTPUT_INDENTATION + task.toString());
        System.out.println(String.format(OUTPUT_INDENTATION + "You now have %d task(s) in the list.", tasks.size()));
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected static void removeAllTasks() {
        tasks.removeAllTask();
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        System.out.println(OUTPUT_INDENTATION + "All tasks have been deleted.");
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected static void printAllTasks() {
        System.out.println(OUTPUT_HORIZONTAL_LINE);
        if (tasks.size() <= 0) {
            System.out.println(OUTPUT_INDENTATION + "There are currently no tasks.");
        }
        for (int i = 0; i < tasks.size(); ++i) {
            Task t = tasks.get(i);
            System.out.println(OUTPUT_INDENTATION + (i + 1) + "." + t);
        }
        System.out.println(OUTPUT_HORIZONTAL_LINE);
    }

    protected static void quit() {
        Serializer.serialize(tasks);
        printGoodbyeMessage();
        System.exit(0);
    }
}
