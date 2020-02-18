import java.io.FileNotFoundException;

/**
 * Represents a Duke that is to be used on the GUI.
 */
public class GuiDuke extends Duke {

    /**
     * Constructor for GuiDuke that takes in the path to the file that
     * contains the saved task list from previous sessions.
     *
     * @param filePath path to the storage file
     */
    public GuiDuke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            // fail silently
        }
    }

    /**
     * Constructor for GuiDuke that uses a default expected file path for
     * storage file.
     */
    public GuiDuke() {
        this(expectedStoragePath);
    }

    /**
     * Returns the response of GuiDuke to the user input.
     *
     * @param input the user input
     * @return the response to the user input
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);
        return ui.getResponse();
    }

    public boolean isExit(String command) {
        return Parser.parse(command).isExit();
    }
}
