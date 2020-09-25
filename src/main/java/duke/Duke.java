package duke;

import duke.command.Command;
import duke.io.CommandParser;
import duke.task.TasksList;
import duke.io.Ui;
import duke.io.Storage;

import duke.exception.DukeException;

/**
 * Manages Duke. It loops getting user input commands and executing them.
 */
public class Duke {
    public static final String TASKS_LIST_SAVE_PATH = "data/tasksList.txt";

    public static TasksList tasks;
    public static Ui ui;
    public static Storage storage;

    public static boolean programIsRunning = true;

    /**
     * Starting method of the program
     * @param args Arguments provided by the user when first starting the program
     */
    public static void main(String[] args) {
        enter();
        mainLoop();
        exit();
    }

    /**
     * Initializes various common objects that are passed as parameters to commands.
     * Greets the user.
     */
    public static void enter() {
        ui = new Ui();
        storage = new Storage(TASKS_LIST_SAVE_PATH);
        try {
            tasks = storage.loadTasksList();
            ui.printDataLoadSuccess();
        } catch (DukeException e) {
            ui.handleException(e);
            tasks = new TasksList();
        }

        ui.printHomeScreen();
    }

    /**
     * A continuous loop that runs as long as the program is running.
     * Asks for user input which is parsed to generate a command object
     * following a Command Pattern. These commands are then executed.
     * This process loops till program is made to exit.
     *
     * This program also handle exception throw by command execution.
     */
    public static void mainLoop() {
        while(programIsRunning) {
            try{
                String input = ui.getInput();
                String[] separatedInput = ui.separateCommandAndArguments(input);
                Command command = CommandParser.parseCommand(separatedInput);
                command.execute(tasks, ui ,storage);

                if (command.isExit) {
                    break;
                }
            } catch(DukeException exception) {
                ui.handleException(exception);
            }
        }
    }

    /**
     * Exits the Program and says goobye to user
     */
    public static void exit() {
        programIsRunning = false;
        ui.printExitScreen();
    }
}
