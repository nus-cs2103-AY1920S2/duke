package duke.commands;

import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;

/**
 * The <code>DeleteCommand</code> class represents a delete command to delete a task from the
 * task list.
 */
public class DeleteCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Removed task:\n";
    private static final String MESSAGE_FAILURE = "Invalid task id. Please enter a valid id number!";
    
    private final String id;
    
    public DeleteCommand(String id) {
        this.id = id;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        try {
            return MESSAGE_SUCCESS + taskList.remove(Integer.parseInt(id));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
