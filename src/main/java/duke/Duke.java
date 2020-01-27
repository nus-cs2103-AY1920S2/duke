package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.getTaskListing());
        } catch(DukeException e) {
            ui.reply(e.getMessage());
        }
    }

    public void run() {
        ui.greet();
        boolean isExitLoop = false;
        while(!isExitLoop) {
            try {
                String userInput = ui.getUserInput();
                ui.printUserCommand(userInput);
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                isExitLoop = command.isExitLoop();
            } catch(DukeException e) {
                ui.reply(e.getMessage());
            }
        }
        storage.closeScanner();
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
