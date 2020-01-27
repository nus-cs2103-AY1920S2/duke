package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;


/**
 * <h1>Command class</h1>
 * The Command class basically take note of what type of Command they are and the data required
 * for individual command, which is save in two variable, type and details respectively. Different type
 * of command will execute differently by run the same method, execute.
 *
 * @author  Eng Xuan En
 */
public class Command {
    protected CommandType type;
    protected List<String> details;
    protected boolean isExit;

    /**
     * Constructor of Command class which requires the command type and set the variable
     * isExit to false.
     * @param type type of the command
     */
    public Command(CommandType type) {
        this.type = type;
        this.isExit = false;
    }

    /**
     * Constructor of Command class which requires both command type and a list of Strings
     * which store the relevant data required for certain command. Following is how the List of Strings
     * organized:
     *          index               data
     *            0                 command type
     *            1                 description of the task
     *            2                 due date of the "Deadline" task / duration of the "Event" task
     * @param type type of the command
     * @param details data required for certain command
     */
    public Command(CommandType type, List<String> details) {
        this.type = type;
        this.details = details;
        this.isExit = false;
    }

    /**
     * Get the command type.
     * @return type of the command
     */
    public CommandType getCommandType() {
        return type;
    }

    /**
     * Execute the command based on the type of the command.
     * @param taskList Stored the tasks when the program runs
     * @param storage Stored the tasks when task listing being edit
     * @param ui Print the message out to console
     * @throws DukeException occurs when the method is called as parent class
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException{
        throw new DukeException("To be implemented in child class");
    }

    public boolean isExitLoop() {
        return isExit;
    }
}
