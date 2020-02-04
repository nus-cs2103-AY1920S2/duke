package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeIoException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Construct a chatbot instance with saved data at default path.
     */
    public Duke() {
        String homeDirectory = System.getProperty("user.home");
        Path storageFilePath = Paths.get(
                homeDirectory,
                "code",
                "duke",
                "data",
                "duke.txt"
        );

        ui = new Ui();
        storage = new Storage(storageFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeIoException e) {
            ui.print("can't load file.");
            tasks = new TaskList();
        }
    }

    /**
     * Main method that instantiate an instance and invoke run method.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Read in user command until exit command is fed.
     */
    public void run() {
        ui.greet();

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    String getResponse(String input) {
        String response = "";

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            response = String.join("\n", ui.getLastResponse());
        } catch (DukeException e) {
            response = e.getLocalizedMessage();
        }

        return response;
    }
}
