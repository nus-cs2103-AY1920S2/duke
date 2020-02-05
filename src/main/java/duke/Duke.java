package duke;

import duke.DukeException;
import duke.command.Command;
import duke.common.Message;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object given the name of the bot, 
     * and the save file path. New ui will be instantiated from
     * the name, and storage will be instantiated form the file
     * path. The tasklist will obtain the saved task, if available.
     * @param botName The name of the bot.
     * @param filePath The path of the save file.
     */
    public Duke(String botName, String filePath) {
        ui = new Ui(botName);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke bot with an interface.
     */
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