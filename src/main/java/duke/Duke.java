package duke;

import duke.command.Command;
import duke.io.CommandParser;
import duke.task.TasksList;
import duke.io.Ui;
import duke.io.Storage;

import duke.exception.DukeException;

public class Duke {
    public static final String TASKS_LIST_SAVE_PATH = "data/tasksList.txt";

    public static TasksList tasks;
    public static Ui ui;
    public static Storage storage;

    public static boolean programIsRunning = true;

    public static void main(String[] args) {
        new Duke(TASKS_LIST_SAVE_PATH).run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasksList();
            ui.printDataLoadSuccess();
        } catch (DukeException e) {
            ui.handleException(e);
            tasks = new TasksList();
        }
    }

    public static void run(){
        enter();
        mainLoop();
        exit();
    }
    public static void enter() {
        ui.printHomeScreen();
    }

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

    public static void exit() {
        programIsRunning = false;
        ui.printExitScreen();
    }
}