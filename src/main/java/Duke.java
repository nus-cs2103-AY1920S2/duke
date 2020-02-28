import java.io.*;

/**
 * Represents a Duke robot that deal with multiple tasks.
 */
public class Duke {
    /**
     * The Storage used.
     */
    private Storage storage;
    /**
     * The list of task.
     */
    private TaskList tasks;
    /**
     * The user interface.
     */
    private Ui ui;
    /**
     * The Parser used.
     */
    private Parser parser;

    /**
     * Creates a new Duke with the given filePath.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.parser = new Parser(ui, tasks, storage);
        parser.run();
    }

    /**
     * Runs Duke with the given filePath.
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\h1430\\Documents\\CS2103T\\duke\\src\\main\\java\\data\\duke.txt").run();
    }
}