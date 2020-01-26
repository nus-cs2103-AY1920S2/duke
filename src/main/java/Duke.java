import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;

public class Duke {
    private static final String FILE_SEPARATOR = File.separator;
    // Map project path to the directory from which you run your program
    public static final String PROJECT_ROOT_PATH = Paths.get("").toAbsolutePath().toString();
    private static String dataDirectoryPath = PROJECT_ROOT_PATH + FILE_SEPARATOR + "data";
    private static String saveFilePath = dataDirectoryPath + FILE_SEPARATOR + "duke.txt";
    protected static final String HORIZONTAL_BAR =
            "____________________________________________________________";
    protected static final String NEWLINE = System.lineSeparator();
    protected static final String INDENTATION = "    ";
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected ArrayList<Task> tasks = new ArrayList<>();
    HashMap<String, CommandType> validCommands;

    public Duke() {
        setupDataDirectory();
        createSaveFile();
        loadSaveFile();
        setupValidCommands();
    }

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

    protected void setupValidCommands() {
        this.validCommands = new HashMap<>();
        this.validCommands.put("deadline", CommandType.DEADLINE);
        this.validCommands.put("event", CommandType.EVENT);
        this.validCommands.put("todo", CommandType.TODO);
        this.validCommands.put("list", CommandType.LIST);
        this.validCommands.put("bye", CommandType.BYE);
        this.validCommands.put("done", CommandType.DONE);
        this.validCommands.put("delete", CommandType.DELETE);
    }

    protected void setupDataDirectory() {
        try {
            // Create directories along path if they don't exist
            Files.createDirectories(Paths.get(dataDirectoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void createSaveFile() {
        try {
            // Create a new file, exception will be thrown if file already exists
            Files.createFile(Paths.get(saveFilePath));
        } catch (FileAlreadyExistsException e) {
            // File exists
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void loadSaveFile() {
        try {
            BufferedReader saveFile = new BufferedReader(new FileReader(saveFilePath));
            // Load data into tasks
            String line = saveFile.readLine();
            /* Format of save file
            [task type],[complete status],[task information]...
            Example:
            todo,1,read book
            deadline,0,return book,June 6th
            event,0,project meeting,Aug 6th 2-4pm
            */
            while (line != null) {
                // Store task
                String[] taskWords = line.split(",");
                boolean isDone = taskWords[1].equals("1");
                String description = taskWords[2];
                switch(taskWords[0].toLowerCase()) {
                case "todo":
                    tasks.add(new Todo(taskWords[2], isDone));
                    break;
                case "deadline":
                    String deadline = taskWords[3];
                    tasks.add(new Deadline(description, deadline, isDone));
                    break;
                case "event":
                    String eventTime = taskWords[3];
                    tasks.add(new Event(description, eventTime, isDone));
                    break;
                default:
                    break;
                }
                line = saveFile.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Used to catch any exceptions resulting from executing commands.
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
            if (command.length() == 0) {
                printTextWithIndentation(HORIZONTAL_BAR);
                printTextWithIndentation("404 Not Found... Are you there?");
                printTextWithIndentation(HORIZONTAL_BAR);
                continue;
            }
            if (!validCommands.containsKey(commandWords[0])) {
                // First word of command does not match list of valid commands
                throw new DukeException(DukeException.exceptionIcon +
                        " OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            // Check first word of command
            switch (commandWords[0]) {
            case "bye":
                // User request for exit
                updateSaveFile();
                break;
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
                    updateSaveFile();
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
                updateSaveFile();
                break;
            case "deadline":
                // Get deadline, find index of "/by"
                String deadlineDelimiter = "/by";
                // Verify user input
                verifyDeadlineInput(command, deadlineDelimiter);
                int deadlineDelimiterIndex = command.indexOf(deadlineDelimiter);
                int deadlineDelimiterLength = deadlineDelimiter.length();
                // Get first word's index for deadline
                // 1 additional character is considered for whitespace
                int deadlineStartIndex = deadlineDelimiterIndex + deadlineDelimiterLength + 1;
                // Remove first word "deadline" and remove delimiter word e.g. " /by "
                String deadlineDescription = command.substring("deadline".length() + 1,
                        deadlineStartIndex - deadlineDelimiterLength - 2);
                String deadline = command.substring(deadlineStartIndex);
                // Add new task
                try {
                    Task newDeadlineTask = new Deadline(deadlineDescription, deadline);
                    tasks.add(newDeadlineTask);
                    printTaskAddition(newDeadlineTask);
                    updateSaveFile();
                } catch (DateTimeException e) {
                    // Given deadline string was not in correct format
                    throw new DukeException("Given deadline task due date was not in correct format" +
                            ": [yyyy-mm-dd]");
                }
                break;
            case "event":
                // Find index of delimiter
                String eventDelimiter = "/at";
                // Verify user input
                verifyEventInput(command, eventDelimiter);
                int eventDelimiterIndex = command.indexOf(eventDelimiter);
                // Get event description, account for whitespace before delimiter
                String eventDescription = command.substring("event".length() + 1,
                        eventDelimiterIndex - 1);
                // Get event time, account for whitespace after delimiter
                String eventTime = command.substring(eventDelimiterIndex + eventDelimiter.length() + 1);
                // Add new event to task list
                try {
                    Task newEvent = new Event(eventDescription, eventTime);
                    tasks.add(newEvent);
                    printTaskAddition(newEvent);
                    updateSaveFile();
                } catch (DateTimeException e) {
                    // Given event time could not be converted a valid date
                    throw new DukeException("Given event task due date was not in correct format" +
                            ": [yyyy-mm-dd]");
                }
                break;
            case "delete":
                try {
                    int taskNumberToDelete = Integer.parseInt(commandWords[1]);
                    Task removedTask = tasks.remove(taskNumberToDelete - 1);
                    printTaskDeletion(removedTask);
                    updateSaveFile();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid task number given for deletion...");
                }
                break;
            default:
                break;
            }
        }
        reader.close();
    }

    /**
     * Throws DukeException if given command has invalid parameters.
     * @param command used for checking user input
     * @param eventDelimiter used to separate event description and event time
     * @throws DukeException for invalid command parameters
     */
    protected void verifyEventInput(String command, String eventDelimiter) throws DukeException {
        int eventDelimiterIndex = command.indexOf(eventDelimiter);
        int eventDelimiterLength = eventDelimiter.length();
        int commandLength = command.length();

        if (commandLength == "event".length()) {
            // Empty event command given (e.g. "event")
            throw new DukeException(DukeException.exceptionIcon +
                    " Wrong input format for adding an event... " +
                    "Format: event [description] /at [event time]");
        }
        if (!command.contains(eventDelimiter)) {
            // No delimiter present (e.g. "event project meeting Mon 2-4pm")
            throw new DukeException(DukeException.exceptionIcon +
                    " Wrong input format for adding an event... " +
                    "Format: event [description] /at [event time]");
        }
        if (eventDelimiterIndex + eventDelimiterLength == commandLength) {
            // Delimiter is at the end of command (e.g. "event /at")
            throw new DukeException(DukeException.exceptionIcon +
                    " Wrong input format for adding an event... " +
                    "Format: event [description] /at [event time]");
        }
    }

    /**
     * Throws DukeException if given command has any invalid parameters.
     * @param command used to check user input
     * @param deadlineDelimiter used to separate deadline description and deadline due date
     * @throws DukeException for invalid command parameters
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

    protected void updateSaveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
            // Write all tasks to file
            for (Task task : tasks) {
                writer.write(task.stringToSaveToDisk());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints out newly added task information.
     * @param task used for printing information related to task
     */
    protected void printTaskAddition(Task task) {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Got it. I've added this task:");
        // Add more indentation for task description
        printTextWithIndentation("  " + task.toString());
        printTextWithIndentation("Now you have " + tasks.size() + " tasks in the list.");
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Mark a given task as done and print out updated task information.
     * @param task to mark as done
     */
    protected void markTaskAsDone(Task task) {
        task.markAsDone();
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Nice! I've marked this task as done:");
        printTextWithIndentation(task.toString());
        printTextWithIndentation(HORIZONTAL_BAR);
    }

    /**
     * Deletes a given task and prints information about deleted task.
     * @param task to be deleted
     */
    protected void printTaskDeletion(Task task) {
        printTextWithIndentation(HORIZONTAL_BAR);
        printTextWithIndentation("Noted. I've removed this task:");
        printTextWithIndentation("  " + task.toString());
        printTextWithIndentation("Now you have " + tasks.size() + " tasks in the list.");
        printTextWithIndentation(HORIZONTAL_BAR);
    }
}
