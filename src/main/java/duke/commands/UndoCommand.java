package duke.commands;

import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;

/**
 * The <code>UndoCommand</code> class represents a undo command to mark a task from the
 * task list as not done.
 */
public class UndoCommand extends Command {
    private static final String MESSAGE_SUCCESS = "o.o well... good luck completing the task:\n";
    private static final String MESSAGE_FAILURE = "Invalid task id. Please enter a valid id number!";
    
    private final String id;
    
    public UndoCommand(String id) {
        this.id = id;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        try {
            return MESSAGE_SUCCESS + taskList.markAsIncomplete(Integer.parseInt(id));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
