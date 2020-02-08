package duke;

import duke.command.Command;
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
        storage = new Storage(saveFile);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // Did not load tasks from save file
            ui.showLoadingError();
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
            // Run process command, check if user has terminated program
            try {
                Optional<Command> c = Parser.parse(ui.readCommand(reader));
                assert c.isPresent() : "Parser did not return a command";
                c.get().execute(tasks, ui, storage);
                requestExit = c.get().isExit();
            } catch (IOException ioException) {
                ui.unableToReadUserInput();
            } catch (DukeException dukeException) {
                // Print error message
                ui.showExceptionMessage(dukeException);
            }
        }
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
            Optional<Command> command = Parser.parse(input);
            assert command.isPresent() : "Parser did not return a command";
            command.get().execute(tasks, ui, storage);
        } catch (DukeException dukeException) {
            // Display error message
            System.out.print(dukeException.getMessage());
        }
        response = output.toString();
        // Remove all horizontal divider present
        String indentation = Ui.INDENTATION;
        String horizontalBar = Ui.HORIZONTAL_BAR;
        while (response.contains(indentation)) {
            response = response.replace(indentation, "");
        }
        while (response.contains(horizontalBar)) {
            response = response.replace(horizontalBar, "");
        }
        response = response.trim();
        // Reset stdout for duke
        System.setOut(System.out);
        return response;
    }
}
