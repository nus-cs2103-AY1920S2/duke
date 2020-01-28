import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The Duke Program contains main method.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class Duke {

    /**
     * Main class.
     *
     * @param args unused.
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
