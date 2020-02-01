package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * <h1>Duke Class</h1>
 * Main program of the Duke program.
 *
 * @author Eng Xuan En
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Class constructor of Duke.
     *
     * @param filePath the location where the tasks being stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.getTaskListing());
        } catch (DukeException e) {
            ui.reply(e.getMessage());
        }
    }

    /**
     * Run the main part of Duke programme. Repeat read and process the user input till the user
     * type bye.
     */
    public void run() {
        ui.greet();
        boolean isExitLoop = false;
        while (!isExitLoop) {
            try {
                String userInput = ui.getUserInput();
                ui.printUserCommand(userInput);
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                isExitLoop = command.isExitLoop();
            } catch (DukeException e) {
                ui.reply(e.getMessage());
            }
        }
        storage.closeScanner();
        ui.closeScanner();
    }

    /**
     * Run main programme here.
     *
     * @param args input from the console
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
