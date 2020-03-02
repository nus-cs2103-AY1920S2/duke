package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.core.Message;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexException;

/**
 * Represents an update description command.
 */
public class UpdateDescriptionCommand extends Command {
    /**
     * Constructs a fresh instance of the update description command.
     * @param input The user input.
     */
    public UpdateDescriptionCommand(String input) {
        super(input);
    }

    /**
     * Executes the Update Description command for the program to update a task description.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling ifthe command is invalid.
     * @throws TaskIndexException An exception if the task index provided is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException, InvalidCommandException {
        try {
            String[] split = this.input.split(" ");
            int idx = Integer.parseInt(split[2]);
            storage.updateDescription(idx, input);
            return tasks.updateDescription(idx - 1, input);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.UPDATE_DESC_ERROR);
        } 
    }
}