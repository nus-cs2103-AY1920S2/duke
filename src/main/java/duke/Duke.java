package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Instantiates a Duke instance with a path to a save file.
     * @param filePath The save file location relative to the project root
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
        this.ui = new Ui();
    }

    /**
     * The main method of the Duke class.
     * @param args Arguments to be passed into Duke
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Starts the Duke program after initialisation.
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isByeCommand = false;
        while (!isByeCommand) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isByeCommand = c.isByeCommand();
            } catch (DukeException ex) {
                ui.printMessage(ex.getMessage());
            }
        }

        Ui.printMessage("Bye! Hope you visit again soon!");
    }
}
