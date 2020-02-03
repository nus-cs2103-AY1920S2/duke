package duke;

import duke.command.Command;

public class Controller {
    private Storage storageController;

    public Controller(Storage storageController) {
        this.storageController = storageController;
    }

    public boolean execute(Command command) {
        return command.execute(storageController, storageController.generateTaskList());
    }
}
