package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * ByeCommand inherits from the Command class.
 * Terminates the program upon execution.
 * @author Lua Jia Zheng.
 */
public class ByeCommand extends Command {

    /**
     * Terminates the program.
     * @param isExit always set to true to terminate the program.
     */
    public ByeCommand(Boolean isExit) {
        super(isExit);
    }

    /**
     * List of command to do when exiting the program.
     * @param tasklist tasklist loaded into storage.
     * @param ui duke.Duke.duke.ui.Ui Prints exit message.
     * @param storage storage used to store data.
     * @return Returns a duke.command.CommandResult object.
     */
    public CommandResult execute(TaskList tasklist, Ui ui, Storage storage) {
        try {
            ui.printGoodBye();
            storage.saveTask(tasklist);
            return new CommandResult("bye");
        } catch (IOException ex) {
            return new CommandResult("IO Exception");
        }
    }

}
