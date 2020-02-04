package duke;

import duke.DukeException;
import duke.command.Command;
import duke.common.Message;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String botName, String filePath) {
        ui = new Ui(botName);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        ui.showMessage(Message.GREET);
        ui.newLine();

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showPrompt();
                String userCommand = ui.readCommand();
                ui.newLine();

                Command command = Parser.parse(userCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
            ui.newLine();
        }

        ui.close();
    }
}