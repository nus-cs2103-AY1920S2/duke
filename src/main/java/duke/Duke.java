package duke;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import duke.command.Command;
import duke.command.CommandParser;
import duke.command.CommandResponse;
import duke.exception.IncorrectCommandException;
import duke.exception.StorageOperationException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UiManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static duke.common.Messages.INVALID_ENCODING_ERROR_MSG;

/**
 * Initializes the application and starts the interaction with the user.
 */
public class Duke extends Application {
    private TaskList taskList;
    private Storage storage;
    private final UiManager ui;
    private final LinkedList<Command> commandHistory;
    private boolean hasFileStorageError;

    /**
     * Constructs a new Duke instance .
     */
    public Duke() {
        // Set up the UI.
        ui = new UiManager();
        commandHistory = new LinkedList<>();
        hasFileStorageError = false;
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage, this);
    }

    /**
     * Executes a single input command from the user and return the response messages.
     *
     * @param input The input command from the user.
     * @return The response messages.
     */
    public String parseAndExecuteSingleCommand(String input) throws IncorrectCommandException,
            StorageOperationException {
        Command command;
        try {
            command = CommandParser.parse(input);

            // Handles ExitCommand
            if (command.isExitCommand()) {
                exit();
            }

            // Handles file storage error
            if (hasFileStorageError) {
                throw new StorageOperationException(INVALID_ENCODING_ERROR_MSG);
            }

            // Handles UndoCommand
            if (command.isUndoCommand()) {
                CommandResponse commandResponse = commandHistory.pop().undo(taskList);
                storage.saveTaskListToStorage(taskList);
                return commandResponse.toString();
            }

            // All other commands
            CommandResponse commandResponse = command.execute(taskList);
            if (command.canBeUndone()) {
                commandHistory.push(command);
            }
            storage.saveTaskListToStorage(taskList);
            return commandResponse.toString();
        } catch (NoSuchElementException e) {
            throw new IncorrectCommandException("Nothing to undo");
        } catch (IncorrectCommandException | StorageOperationException e) {
            // Handles file storage error
            if (hasFileStorageError) {
                throw new StorageOperationException(INVALID_ENCODING_ERROR_MSG);
            }
            throw e;
        }

    }

    /**
     * Initializes the storage for the application.
     */
    public void initStorage() throws StorageOperationException {
        storage = new Storage();
        taskList = storage.loadTaskListFromStorage();
    }

    /**
     * Enters storage error mode.
     * Used when a StorageOperationException prevents the normal operation of the application.
     * Only the ExitCommand will be able to run.
     */
    public void enterStorageErrorMode() {
        hasFileStorageError = true;
    }

    /**
     * Exits the application.
     */
    private void exit() {
        Platform.exit();
        System.exit(0);
    }
}
