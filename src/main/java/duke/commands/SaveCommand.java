package duke.commands;

import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;

import java.io.IOException;

public class SaveCommand extends Command {
    private static final String MESSAGE_SUCCESS = "Tasks saved! You may now exit the program.";
    private static final String MESSAGE_FAILURE = "Sorry! I couldn't save your tasks to disk :(";
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        try {
            storage.save(taskList.formatForStorage());
            return MESSAGE_SUCCESS;
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
