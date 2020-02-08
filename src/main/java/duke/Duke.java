package duke;

import duke.command.Command;
import duke.ui.Ui;

/**
 * Represents the Duke for the application.
 * The Duke will run the program that interact with user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Constructs a Duke with Storage, TaskList and Ui being initialised.
     *
     * @param filePath The file path to store data of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the Duke's storage.
     *
     * @return The Duke's storage.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns the Duke's tasks.
     *
     * @return The Duke's tasks.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Returns isExit.
     *
     * @return isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns the messages to be shown to user after handling input.
     *
     * @param input The user input.
     * @return The messages to be shown to user.
     */
    public String getResponse(String input) {
        assert !input.equals("") : "Please input a command!";
        try {
            Command command = Parser.parse(input);
            isExit = command.isExit();
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the program running.
     */
    public void run() {
        System.out.println(Ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                System.out.println(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
