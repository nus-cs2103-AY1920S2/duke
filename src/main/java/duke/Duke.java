package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the non-GUI Duke application.
 * Initialises the program and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs Duke, initialise the Ui, storage and task list.
     *
     * @param filePath to store the list of tasks in the disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the program until "bye" command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showTopLine();
                Command c = Parser.parse(fullCommand);
                String acknowledgement = c.execute(taskList, ui, storage);
                ui.showAcknowledgement(acknowledgement);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showBottomLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
