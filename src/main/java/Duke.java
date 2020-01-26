import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.HashMap;

public class Duke {
    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    HashMap<String, CommandType> validCommands;

    public Duke(String saveFile) {
        ui = new Ui();
        storage = new Storage(saveFile);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // Did not load tasks from save file
            ui.showLoadingError();
            tasks = new TaskList();
        }
        setupValidCommands();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    public void run() {
        ui.greet();
        boolean requestExit = false;
        while (!requestExit) {
            // Run process command, check if user has terminated program
            requestExit = processCommandWrapper(reader);
        }
        ui.goodbye();
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
            ui.unableToReadUserInput();
            normalExit = false;
        } catch (DukeException dukeException) {
            // Print error message
            ui.showExceptionMessage(dukeException);
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
                ui.commandNotFound();
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
                storage.updateSaveFile(tasks);
                break;
            case "list":
                ui.listTasks(tasks);
                break;
            case "done":
                // Get task to mark as done
                try {
                    int taskNumber = Integer.parseInt(commandWords[1]);
                    Task task = tasks.get(taskNumber - 1);
                    ui.markTaskAsDone(task);
                    storage.updateSaveFile(tasks);
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
                Task newTodoTask = tasks.addTodoTask(todoDescription);
                // Print out information about added task
                ui.printTaskAddition(newTodoTask, tasks.size());
                storage.updateSaveFile(tasks);
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
                    Task newDeadlineTask = tasks.addDeadlineTask(deadlineDescription, deadline);
                    ui.printTaskAddition(newDeadlineTask, tasks.size());
                    storage.updateSaveFile(tasks);
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
                    Task newEvent = tasks.addEventTask(eventDescription, eventTime);
                    ui.printTaskAddition(newEvent, tasks.size());
                    storage.updateSaveFile(tasks);
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
                    ui.printTaskDeletion(removedTask, tasks.size());
                    storage.updateSaveFile(tasks);
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
}
