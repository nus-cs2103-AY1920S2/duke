package duke;

import duke.command.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
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
        new Duke("duke.txt").run(reader);
    }

    /**
     * Process input given by user and execute relevant actions.
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
}
