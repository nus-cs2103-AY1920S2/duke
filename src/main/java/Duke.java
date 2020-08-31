import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Parser;

import java.io.FileNotFoundException;

/**
 * This is a simulation of a chat bot called Duke.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new Duke with the given data storage path.
     *
     * @param path The file path of Duke data file.
     */
    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Generate Duke response according to user input.
     *
     * @param input User put in.
     * @return A string of Duke response.
     */
    public String getResponse(String input) {
        return Parser.parse(input).run(ui, storage,tasks);
    }

    /**
     * Shows Duke logo on the user page.
     *
     * @return a String of Duke's logo.
     */
    public String getLogo() {
        return ui.showLogo();
    }

    /**
     * Shows Duke greeting sentences on the user page.
     *
     * @return Duke's greeting sentences.
     */
    public String getGreet() {
        return ui.showGreet();
    }

}
