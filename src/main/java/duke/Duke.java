/**
 * Main class to run Duke program
 * <p>This program acts as a scheduler that can add tasks to be done, tasks with a deadline and
 * upcoming events </p>
 *
 * @param filePath - File path where schedule is saved. File path is relative to src folder
 */

package duke;

import duke.storage.Storage;
import duke.exceptions.DukeException;
import duke.ui.Ui;
import duke.tasks.*;
import duke.commands.*;
import duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke/data/tasks.txt").run();
    }
}