package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Driver for duke project.
 */
public class Duke {
    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
        new Duke("duke.txt").run(reader);
    }

    /**
     * Process input given by user and execute relevant actions.
     *
     * @param reader used for user input
     */
    public void run(BufferedReader reader) {
        ui.greet();
        boolean requestExit = false;
        while (!requestExit) {
            // Run process command, check if user has terminated program
            try {
                Command c = Parser.parse(ui.readCommand(reader));
                c.execute(tasks, ui, storage);
                requestExit = c.isExit();
            } catch (IOException ioException) {
                ui.unableToReadUserInput();
            } catch (DukeException dukeException) {
                // Print error message
                ui.showExceptionMessage(dukeException);
            }
        }
        ui.goodbye();
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
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
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
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
