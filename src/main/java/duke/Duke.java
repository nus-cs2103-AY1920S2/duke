package duke;

import duke.command.Command;
import duke.ui.Ui;

/**
 * Main class for the duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Constructs a Duke object and initialises the Storage, Ui and TaskList.
     *
     * @param filePath The file path where the tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            ui.print(ui.showError(e.getMessage()));
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Generates a response to user input.
     *
     * @param input String containing user input.
     * @return A string with the response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            isExit = command.isExit();
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.print(Ui.showWelcome());
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                isExit = command.isExit();
                ui.print(command.execute(tasks, ui, storage));
            } catch (DukeException e) {
                ui.print(ui.showError(e.getMessage()));
            }
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
