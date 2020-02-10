package duke;

import duke.command.Command;
import duke.other.Parser;
import duke.other.Storage;
import duke.other.Ui;
import duke.task.TaskList;


/**
 * Represents Duke, the chat bot.
 */
public class Duke {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;


    /**
     * Constructs a Duke object.
     *
     * @param filePath File path to the file that stores the Tasks data
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
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (Exception e) {
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
        Parser p = new Parser();
        Command command = p.parse(input);
        String response = "";
        response = command.execute(tasks, ui);
        return response;
    }


}
