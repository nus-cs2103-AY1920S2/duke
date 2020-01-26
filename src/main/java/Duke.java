import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;

public class Duke {
    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
    private Parser parser;
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    public void run() {
        ui.greet();
        boolean requestExit = false;
        while (!requestExit) {
            // Run process command, check if user has terminated program
            try {
                processCommands(reader);
                requestExit = true;
            } catch (IOException ioException) {
                ui.unableToReadUserInput();
            } catch (DukeException dukeException) {
                // Print error message
                ui.showExceptionMessage(dukeException);
            }
        }
        ui.goodbye();
    }

    /**
     * Process input given by user and execute relevant actions.
     *
     * @param inputReader used for user input
     */
    protected void processCommands(BufferedReader inputReader) throws DukeException, IOException {
        parser = new Parser(ui.readCommand(inputReader));
        if (parser.isEmptyCommand()) {
            ui.commandNotFound();
        }
        while (!parser.isExitCommand()) {
            if (parser.isValidCommand()) {
                switch (parser.getCommandType()) {
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
                        int taskNumber = parser.getCommandTaskNumber();
                        Task task = tasks.get(taskNumber - 1);
                        ui.markTaskAsDone(task);
                        storage.updateSaveFile(tasks);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new DukeException("Invalid Task Number given!");
                    }
                    break;
                case "todo":
                    // Check if valid command is given
                    if (parser.numberOfCommandArguments() == 0) {
                        throw new DukeException(DukeException.exceptionIcon +
                                " The description of a todo cannot be empty...");
                    }
                    String todoDescription = parser.getDescription();
                    Task newTodoTask = tasks.addTodoTask(todoDescription);
                    // Print out information about added task
                    ui.printTaskAddition(newTodoTask, tasks.size());
                    storage.updateSaveFile(tasks);
                    break;
                case "deadline":
                    // Verify user input
                    parser.verifyDeadlineInput();
                    String deadlineDescription = parser.getDescription();
                    String deadline = parser.getDueDate();
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
                    parser.verifyEventInput();
                    String eventDescription = parser.getDescription();
                    String eventTime = parser.getDueDate();
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
                        int taskNumberToDelete = parser.getCommandTaskNumber();
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
            } else {
                // First word of command does not match list of valid commands
                throw new DukeException(DukeException.exceptionIcon +
                        " OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            // Get next command
            parser = new Parser(ui.readCommand(inputReader));
        }
    }
}
