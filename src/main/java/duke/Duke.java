package duke;

import java.io.IOException;

import command.Command;


/**
 * This class is the main driving chat bot.
 **/
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object, it takes in a file path
     * to obtain information from the file.
     * @param filePath The file path to the file to be modified
     **/
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException de) {
            ui.showLoadingError(de);
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Constructs an empty Duke object.
     */
    public Duke() {
    }

    /**
     * Processes the input from user.
     **/
    public String processInput(String fullCommand) {
        Command c = Parser.parse(fullCommand);
        return c.execute(tasks, ui, storage);
    }

    /**
     * Returns the response of the bot
     * according to text as the input.
     * @param text The input from the user of the bot.
     * @return Message from the bot.
     */
    protected String getResponse(String text) {
        return processInput(text);
    }
}
