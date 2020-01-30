package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isLoadedFromStorage = false;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            this.isLoadedFromStorage = true;
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        if (this.isLoadedFromStorage) {
            ui.dukePrompt("Boss, I've got my notebook ready with " + tasks.getSize() + " tasks in it");
        }
        boolean isActive = true;
        while (isActive) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    isActive = false;
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method. Entry point for the Duke program.
     * @param args Command-line arguments. Unused.
     */
    public static void main(final String[] args) {
        new Duke("data/duke.txt").run();
    }
}
