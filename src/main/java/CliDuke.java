import java.io.FileNotFoundException;

public class CliDuke extends Duke {
    /**
     * Constructor for Duke that takes in the path to the file that
     * contains the saved task list from previous sessions.
     *
     * @param filePath path to the storage file
     */
    public CliDuke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke that uses a default expected file path for
     * storage file.
     */
    public CliDuke() {
        this(expectedStoragePath);
    }

    /**
     * Starts the chatbot and continues executing commands until the
     * exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new CliDuke(expectedStoragePath).run();
    }
}
