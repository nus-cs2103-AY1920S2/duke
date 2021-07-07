package duke.main;

import duke.DukeException;
import duke.command.Command;
import duke.command.ListCommand;

/**
 * Main class of the duke programme (aka Brian-bot).
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean isExit = false;
    private boolean isTimerOn = false;

    /**
     * Loads existing task list by searching in filePath. If this path does not exist, programme will create a new file
     * in this path where tasks will be automatically saved to. Also sets up user interface and command parser.
     *
     * @param filePath path of existing task list to load or path of new task list to create.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load(), parser);
        } catch (DukeException de) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves the appropriate response that the bot should give based on the user's input.
     *
     * @param userInput User input
     * @return String representation of the response that the bot gives
     */
    public String getResponse(String userInput) {
        String response;
        try {
            String commandType = parser.getCommandType(userInput, tasks);
            Command command = parser.selectCommand(commandType, userInput, isTimerOn);
            response = command.execute(tasks, ui, storage);
            isExit = command.isExit();
            if (command instanceof ListCommand) {
                isTimerOn = ((ListCommand) command).isTimerOn();
            }
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        assert !response.equals("") : "Empty response";
        return response;
    }

    public boolean getExitStatus() {
        return isExit;
    }
}
