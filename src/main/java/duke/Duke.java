package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * <h1>Duke Class</h1>
 * Main program of the Duke program.
 *
 * @author Eng Xuan En
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isGui;

    /**
     * Class constructor of Duke.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage("data/task.txt");
            taskList = new TaskList(storage.getTaskListing());
        } catch (DukeException e) {
            ui.reply(e.getMessage());
        }
    }

    /**
     * Return the reply message back to display in GUI.
     *
     * @param input input from the user
     * @return reply message in String format
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = Parser.parse(input);
            response = command.executeWithoutReply(taskList, storage, ui);
            assert response != null : "Response is null";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Give greeting message.
     * @return Return greeting message in String format
     */
    public String getGreeting() {
        return ui.greetWithoutPrint();
    }

    /**
     * Run the main part of Duke programme. Repeat read and process the user input till the user
     * type bye.
     */
    public void run() {
        ui.setOutline();
        ui.greet();
        boolean isExitLoop = false;
        while (!isExitLoop) {
            try {
                String userInput = ui.getUserInput();
                ui.printUserCommand(userInput);
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                isExitLoop = command.isExitLoop();
            } catch (DukeException e) {
                ui.reply(e.getMessage());
            }
        }
        storage.closeScanner();
        ui.closeScanner();
    }

    /**
     * Set Ui output to not Gui.
     */
    public void setUiToNotGui() {
        if (!isGui) {
            ui.setNotGui();
        }
    }

    /**
     * Set consoleOutput to false.
     */
    public void setNotGui() {
        isGui = false;
    }

    /**
     * The main programme runs here.
     *
     * @param args input from the console
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setUiToNotGui();
        duke.run();
    }
}
