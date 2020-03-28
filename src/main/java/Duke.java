import java.io.File;
import java.io.IOException;


/**
 *  Duke Class.
 *  The main class to run the whole code.
 *
 * @author Amos Cheong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        // Initialize the taskList from Storage class
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            new File(storage.filePath).createNewFile();
            tasks = new TaskList(storage.load(), ui, storage);

        } catch (IOException myioex) {
            ui.showErrorMessage(myioex.getMessage());
            tasks = new TaskList();
        }

    }
    public TaskList getTaskList() {
        return this.tasks;
    }

    public Storage getStorage() {
        return this.storage;
    }
    /**
     *  Handles message that is parsed in for Duke to answer.
     * @param message The message for Duke to reply to user
     */
    public String dukeAnswer(String message) {
        return "_______________________________________________________" +
                "\n" + message + "\n" +
                "_______________________________________________________";
    }






}