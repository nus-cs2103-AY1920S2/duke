import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    protected static final String HORIZONTAL_BAR =
            "____________________________________________________________";
    protected static final String NEWLINE = System.lineSeparator();
    protected static final String INDENTATION = "    ";
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        boolean requestExit = false;
        while (!requestExit) {
            // Run process command, check if user has terminated program
            requestExit = duke.processCommandWrapper(reader);
        }
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
     * Function used to catch any exceptions resulting from executing commands
     * @param inputReader used to receive user input
     * @return boolean indicating whether user exited normally
     */
    protected boolean processCommandWrapper(BufferedReader inputReader) {
        boolean normalExit = true;
        try {
            processCommands(inputReader);
        } catch (IOException ioException) {
            printTextWithIndentation(HORIZONTAL_BAR);
            printTextWithIndentation("Unable to read user input...");
            printTextWithIndentation(HORIZONTAL_BAR);
            normalExit = false;
        } catch (DukeException dukeException) {
            // Print error message
            printTextWithIndentation(HORIZONTAL_BAR);
            printTextWithIndentation(dukeException.getMessage());
            printTextWithIndentation(HORIZONTAL_BAR);
            normalExit = false;
        }
        return normalExit;
    }

    /**
     * Process input given by user and execute relevant actions.
     * @param inputReader used for user input
     */
    protected void processCommands(BufferedReader inputReader) throws DukeException, IOException {
        reader = inputReader;
        String command = "";
        while (!(command.equals("bye"))) {
            command = reader.readLine();
            // Remove leading and trailing whitespace
            command = command.trim();
            String[] commandWords = command.split("\\s+");
            // No input is given or only whitespace given
            if (commandWords.length == 0 || commandWords[0].equals("")) {
                printTextWithIndentation(HORIZONTAL_BAR);
                printTextWithIndentation("404 Not Found... Are you there?");
                printTextWithIndentation(HORIZONTAL_BAR);
                continue;
            }
            // Check first word of command
            switch (commandWords[0]) {
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
                try {
                    int taskNumber = Integer.parseInt(commandWords[1]);
                    Task task = tasks.get(taskNumber - 1);
                    markTaskAsDone(task);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid Task Number given!");
                }
                break;
            case "todo":
                // Check if valid command is given
                if (command.length() <= "todo".length()) {
                    throw new DukeException(DukeException.exceptionIcon +
                            " The description of a todo cannot be empty...");
                }
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
                int deadlineDelimiterIndex = command.indexOf(deadlineDelimiter);
                int deadlineDelimiterLength = deadlineDelimiter.length();
                verifyDeadlineInput(command, deadlineDelimiter);
                // Get first word's index for deadline
                // 1 additional character is considered for whitespace
                int deadlineStartIndex = deadlineDelimiterIndex + deadlineDelimiterLength + 1;
                // Remove first word "deadline" and remove delimiter word e.g. " /by "
                String deadlineDescription = command.substring("deadline".length() + 1,
                        deadlineStartIndex - deadlineDelimiterLength - 2);
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
        reader.close();
    }

    /**
     * Throws DukeException if given command has any invalid parameters
     * @param command used to check user input
     * @param deadlineDelimiter used to separate deadline description and deadline due date
     * @throws DukeException for invalid command parameter
     */
    protected void verifyDeadlineInput(String command, String deadlineDelimiter) throws DukeException {
        int deadlineDelimiterIndex = command.indexOf(deadlineDelimiter);
        int deadlineDelimiterLength = deadlineDelimiter.length();
        int commandLength = command.length();

        if (commandLength == "deadline".length()) {
            // Empty deadline command given (e.g. "deadline")
            throw new DukeException(DukeException.exceptionIcon +
                    " The description of a deadline cannot be empty...");
        }
        if (!command.contains(deadlineDelimiter)) {
            // No due date given (e.g. "deadline read book")
            throw new DukeException(DukeException.exceptionIcon +
                    " No deadline given... Format: deadline [description] /by [due by]");
        }
        if (deadlineDelimiterIndex + deadlineDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "deadline /by")
            throw new DukeException(DukeException.exceptionIcon +
                    " No deadline given... Format: deadline [description] /by [due by]");
        }
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
