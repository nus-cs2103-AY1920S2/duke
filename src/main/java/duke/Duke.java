package duke;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.Parser;
import duke.exception.InvalidCommandException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for duke.
 * Hold Storage, TaskList and Ui object for application
 * Runs the application until the exit command is called.
 */
public class Duke {
    /** Storage object to deal with the save file. */
    private Storage storage;
    /** TaskList object to store the task list. */
    private TaskList tasks;
    /** Ui object to interact with the user. */
    private Ui ui;

    /**
     * Constructs Duke with the default save directory, as well as welcome and exit messages.
     */
    public Duke() {
        String workingDir = System.getProperty("user.dir");
        Path savePath = Paths.get(workingDir, "data", "duke.txt");
        storage = new Storage(savePath);
        ui = new Ui("     Hello! I'm Duke\n     What can I do for you?"
                , "     Bye. Hope to see you again soon!");
    }

    /**
     * Main program loop.
     * Runs the program until the exit command is called.
     */
    public void runUntilExit() {
        TaskList tasks = null;
        boolean isRunning = true;
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch(IOException e){
            ui.printMessage("     Sorry, I could not read the save file.");
        }
        ui.printWelcomeMessage();
        while (isRunning) {
            try {
                Command command = Parser.parseCommand(ui.getUserInput());
                if (command instanceof ExitCommand) {
                    isRunning = false;
                }
                command.execute(tasks, ui, storage);
            } catch (InvalidCommandException e) {
                ui.printException(e);
            }
        }
    }

    /**
     * Main method that runs the program.
     *
     * @param args takes in a string array of argument for the program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runUntilExit();
    }
}
