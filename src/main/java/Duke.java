import duke.pack.Command;
import duke.pack.DukeException;
import duke.pack.Parser;
import duke.pack.Storage;
import duke.pack.TaskList;
import duke.pack.Ui;

import javafx.fxml.FXML;

/**
 * Represents the chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String path = "data/duke.txt";

    /**
     * Creates a Duke object.
     * @param filePath String representing the file path
     */
    public Duke(String filePath) {
        // following code from module website

        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }


    /**
     * Generates a response to user input.
     * @param input user input command
     */
    public String getResponse(String input) {
        String resp = "";

        try {
            Command comm = Parser.parseCommand(input);
            resp = comm.getResponse(tasks, ui, storage);

        } catch (DukeException e) {
            resp = e.toString();
        }

        return resp;
    }

    /**
     * Starts to run the bot
     */
    public void run() {
        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.receiveInput();
                ui.showLine();
                Command comm = Parser.parseCommand(command);
                comm.execute(tasks, ui, storage);
                isExit = comm.isExit();

            } catch (DukeException e) {
                ui.showError(e);

            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke(path).run();
    }

}
