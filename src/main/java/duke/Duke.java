package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import duke.exception.DukeStorageDirectoryException;
import duke.exception.DukeStorageFileException;
import duke.exception.DukeStorageLoadException;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Optional;

/**
 * Driver for duke project.
 */
public class Duke {
    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        new Duke("duke.txt").run(reader);
    }

    /**
     * Returns a new Duke instance, uses duke.txt for save file.
     */
    public Duke() {
        this("duke.txt");
    }

    /**
     * Return a new instance of Duke class.
     *
     * @param saveFile File name used for saving user data
     */
    public Duke(String saveFile) {
        ui = new Ui();
        loadTasksFromSaveFile(saveFile);
    }

    private void loadTasksFromSaveFile(String saveFile) {
        try {
            storage = new Storage(saveFile);
            tasks = new TaskList(storage.load());
        } catch (DukeStorageLoadException | DukeStorageDirectoryException | DukeStorageFileException e) {
            ui.showExceptionMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Process input given by user and execute relevant actions.
     *
     * @param reader used for user input
     */
    public void run(BufferedReader reader) {
        assert reader != null : "BufferedReader given should not be null";
        ui.greet();
        boolean requestExit = false;
        while (!requestExit) {
            try {
                Optional<Command> c = parseAndExecuteCommand(ui.readCommand(reader));
                if (c.isPresent()) {
                    requestExit = c.get().isExit();
                }
            } catch (IOException ioException) {
                ui.unableToReadUserInput();
            } catch (DukeException dukeException) {
                ui.showExceptionMessage(dukeException);
            }
        }
    }

    private Optional<Command> parseAndExecuteCommand(String input) throws DukeException {
        Optional<Command> c = Parser.parse(input);
        assert c.isPresent() : "Parser did not return a command";
        Optional<TaskList> taskList = c.get().execute(tasks, ui, storage);
        if (isValidUndoCommandTypes(c.get()) && taskList.isPresent()) {
            tasks = taskList.get();
        }
        return c;
    }

    private boolean isValidUndoCommandTypes(Command command) {
        return command instanceof UndoCommand || command instanceof DeleteCommand
                || command instanceof AddTaskCommand || command instanceof DoneCommand;
    }

    /**
     * Returns a response to a given input String command.
     *
     * @param input command String to be executed
     * @return String response
     */
    public String getResponse(String input) {
        String response;
        // Change stdout for duke
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try {
            parseAndExecuteCommand(input);
        } catch (DukeException dukeException) {
            // Display error message
            System.out.print(dukeException.getMessage());
        }
        response = output.toString();
        response = formatResponse(response);
        // Reset stdout for duke
        System.setOut(System.out);
        return response;
    }

    private String formatResponse(String input) {
        // Remove all horizontal divider present
        String indentation = Ui.INDENTATION;
        String horizontalBar = Ui.HORIZONTAL_BAR;
        String response = input;
        while (response.contains(indentation)) {
            response = response.replace(indentation, "");
        }
        while (response.contains(horizontalBar)) {
            response = response.replace(horizontalBar, "");
        }
        response = response.trim();
        return response;
    }
}
