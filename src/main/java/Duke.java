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
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Hello! I'm Duke");
        printTextWithIndentation("What can I do for you?");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Prints goodbye message.
     */
    protected void goodbye() {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Bye. Hope to see you again soon!");
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
                String delimiter = "/by";
                // Get first word's index for deadline
                // 1 additional character is considered for whitespace
                int deadlineStartIndex = command.indexOf(delimiter) + delimiter.length() + 1;
                // Remove first word "deadline" and remove delimiter word e.g. " /by "
                String deadlineDescription = command.substring("deadline".length() + 1,
                        deadlineStartIndex - delimiter.length() - 2);
                String deadline = command.substring(deadlineStartIndex);
                // Add new task
                Task newDeadlineTask = new Deadline(deadlineDescription, deadline);
                tasks.add(newDeadlineTask);
                printTaskAddition(newDeadlineTask);
                break;
            default:
                break;
            }
        }
        scanner.close();
    }

    private void printTaskAddition(Task task) {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Got it. I've added this task:");
        printTextWithIndentation(task.toString());
        printTextWithIndentation("Now you have " + tasks.size() + " tasks in the list.");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    private void markTaskAsDone(Task task) {
        task.markAsDone();
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Nice! I've marked this task as done:");
        printTextWithIndentation(task.toString());
        printTextWithIndentation(HORIZONTAL_BAR);
    }
}
