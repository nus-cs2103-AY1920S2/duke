package duke;

import duke.command.Command;
import duke.other.Storage;
import duke.other.Ui;
import duke.other.DukeException;
import duke.other.Parser;
import duke.task.TaskList;

import java.io.FileNotFoundException;


/**
 * Represents Duke, the chat bot.
 */
public class Duke {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;


    /**
     * Constructs a Duke object.
     * @param filePath File path to the file that stores the Tasks data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke, the chat bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.saveFile(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command parser = (new Parser()).parse(input);
        String response = "";
        if (parser.isExit()) {
            response = "    Bye! See ya later, alligator!";
            storage.saveFile(tasks);
        } else {
            response = parser.execute(tasks, ui, storage);
        }
        return response;
    }


}
