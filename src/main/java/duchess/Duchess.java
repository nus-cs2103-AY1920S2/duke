package duchess;

import duchess.command.Command;
import duchess.exception.DuchessException;
import duchess.io.Parser;
import duchess.storage.Storage;
import duchess.task.TaskList;
import duchess.ui.Ui;

public class Duchess {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duchess(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DuchessException e) {
            this.ui.printLoadingError();
            this.taskList = new TaskList();
        }
    }

    public void run() {
        this.ui.printWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command command = Parser.parse(fullCommand);
                command.execute.apply(fullCommand, this.taskList, this.ui, this.storage);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (DuchessException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }
}
