import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static final String HORIZONTAL_BAR =
            "____________________________________________________________";
    protected static final String NEWLINE = System.lineSeparator();
    protected static final String INDENTATION = "    ";
    protected static Scanner scanner;
    protected ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.processCommands(System.in);
        duke.goodbye();
    }

    /**
     * Prints given text with indentation: specified in Duke class.
     * @param text used for formatting and printing
     */
    protected void printTextWithIndentation(String text) {
        System.out.println(INDENTATION + text);
    }

    /**
     * Prints greeting message.
     */
    protected void greet() {
        String logo = "  __  __        _____       _           _   " + NEWLINE +
                " |  \\/  |      |  __ \\     | |         | |  " + NEWLINE +
                " | \\  / |_ __  | |__) |___ | |__   ___ | |_ " + NEWLINE +
                " | |\\/| | '__| |  _  // _ \\| '_ \\ / _ \\| __|" + NEWLINE +
                " | |  | | |    | | \\ \\ (_) | |_) | (_) | |_ " + NEWLINE +
                " |_|  |_|_|    |_|  \\_\\___/|_.__/ \\___/ \\__|";
        System.out.println(logo);
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Hello friend. Hello friend?");
        printTextWithIndentation("That's lame. Maybe I should give you a name.");
        printTextWithIndentation("But that's a slippery slope, you're only in my head,");
        printTextWithIndentation("we have to remember that.");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Prints goodbye message.
     */
    protected void goodbye() {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Goodbye friend.");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Process input given by user and execute relevant actions.
     */
    protected void processCommands(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            // Remove leading and trailing whitespace
            command = command.trim();
            String[] commandWords = command.split("\\s+");
            // No input is given or only whitespace given
            if (commandWords.length == 0 || commandWords[0].equals("")) {
                continue;
            }
            // Check first word of command
            switch (commandWords[0]) {
            case "bye":
                return;
            case "list":
                printTextWithIndentation(HORIZONTAL_BAR);
                int taskCount = 1;
                printTextWithIndentation("Here are the tasks in your list:");
                for (Task task : tasks) {
                    printTextWithIndentation("" + taskCount + "." + task.toString());
                    taskCount++;
                }
                printTextWithIndentation(HORIZONTAL_BAR);
                break;
            case "done":
                // Get task to mark as done
                int taskNumber = Integer.parseInt(commandWords[1]);
                Task task = tasks.get(taskNumber - 1);
                markTaskAsDone(task);
                break;
            case "todo":
                // Get task description, get substring starting at index 5
                // Remove first word
                String todoDescription = command.substring("todo".length() + 1);
                Task newTodoTask = new Todo(todoDescription);
                tasks.add(newTodoTask);
                // Print out information about added task
                printTaskAddition(newTodoTask);
                break;
            case "deadline":
                // Get deadline, find index of "/by"
                String deadlineDelimiter = "/by";
                // Get first word's index for deadline
                // 1 additional character is considered for whitespace
                int deadlineStartIndex = command.indexOf(deadlineDelimiter) + deadlineDelimiter.length() + 1;
                // Remove first word "deadline" and remove delimiter word e.g. " /by "
                String deadlineDescription = command.substring("deadline".length() + 1,
                        deadlineStartIndex - deadlineDelimiter.length() - 2);
                String deadline = command.substring(deadlineStartIndex);
                // Add new task
                Task newDeadlineTask = new Deadline(deadlineDescription, deadline);
                tasks.add(newDeadlineTask);
                printTaskAddition(newDeadlineTask);
                break;
            case "event":
                // Find index of delimiter
                String eventDelimiter = "/at";
                int eventDelimiterIndex = command.indexOf(eventDelimiter);
                // Get event description, account for whitespace before delimiter
                String eventDescription = command.substring("event".length() + 1,
                        eventDelimiterIndex - 1);
                // Get event time, account for whitespace after delimiter
                String eventTime = command.substring(eventDelimiterIndex + eventDelimiter.length() + 1);
                // Add new event to task list
                Task newEvent = new Event(eventDescription, eventTime);
                tasks.add(newEvent);
                printTaskAddition(newEvent);
                break;
            default:
                break;
            }
        }
        scanner.close();
    }

    protected void throwAndHandleDukeException(String message) {

    }

    protected void printTaskAddition(Task task) {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Got it. I've added this task:");
        // Add more indentation for task description
        printTextWithIndentation("  " + task.toString());
        printTextWithIndentation("Now you have " + tasks.size() + " tasks in the list.");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    protected void markTaskAsDone(Task task) {
        task.markAsDone();
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Nice! I've marked this task as done:");
        printTextWithIndentation(task.toString());
        printTextWithIndentation(HORIZONTAL_BAR);
    }
}
