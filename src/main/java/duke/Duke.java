package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeNoCommandException;
import duke.exception.DukeNoSuchInputException;
import duke.exception.DukeProgramTerminatedException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.utils.TaskList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTaskList();
            ui.printLoadSuccess(storage.getFilePath());
        } catch (DukeException e) {
            this.tasks = new TaskList();
            ui.printLoadFail(e, storage.getFilePath());
        }
    }

    public TaskList getTaskList() {
        return tasks;
    }

    public Storage getStorage() {
        return storage;
    }

    private void run() {
        ui.printWelcome();
        while (true) {
            try {
                String input = ui.readInput();
                Command command = Command.createCommand(input);
                String message = command.execute(this);
                ui.print(message);
            } catch (DukeNoSuchInputException | DukeProgramTerminatedException e) {
                break;
            } catch (DukeNoCommandException e) {
                continue;
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
        ui.printGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
        System.exit(0);
    }
}
