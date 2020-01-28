import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class Duke {

    /**
     * Main class
     * @param args
     */
    public static void main(String[] args) {

        TaskList manager = new TaskList();
        Storage storage = new Storage("../../DataFile.txt");

        manager.addStorage(storage);
        storage.addManager(manager);
        storage.loadFile();
        Ui ui = new Ui(manager);
        ui.frontDesk();

    }

}
