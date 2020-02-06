package gui;

import duke.Controller;
import duke.Storage;
import duke.Ui;
import duke.command.Command;

public class GuiController extends Controller {
    /**
     * Constructs a Controller object with the specified Storage object
     *
     * @param storageController the Storage object
     */
    public GuiController(Storage storageController) {
        super(storageController);

    }


    public String executeGui(Command command) {

        super.execute(command);
        return Ui.getContent();
    }
}
