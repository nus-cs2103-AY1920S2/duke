package seedu.java.util;

/**
 * The Central class. Contains Storage, TaskList & Ui class. Relies on Parser class to handle user's input.
 * run()
 * main()
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static String CLOSE = "0";

    /**
     * A Duke constructor that loads up a file based on a file path.
     * @param filePath - a path to take data from, .txt format
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor with no parameters: intended for launcher class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Boots the program, interacts with the user & auto-save to storage.
     */

    public String getResponse(String input) {
        return tasks.read(input);
    }
}