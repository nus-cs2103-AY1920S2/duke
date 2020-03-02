package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Message;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexException;

/**
 * Represents a delete task command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a fresh instance of a delete command.
     * @param input The user input.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the Delete command for the program to delete a task.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling ifthe command is invalid.
     * @throws TaskIndexException An exception if the task index provided is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws TaskIndexException, InvalidCommandException {
        String[] split = this.input.split(" ");
        if (split.length < 2) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } else if (split.length > 2) {
            throw new InvalidCommandException(Message.DELETE_ERROR);
        }

        try {
            int idx = Integer.parseInt(split[1]);
            storage.deleteTask(idx);
            return tasks.deleteTask(idx - 1);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Message.DELETE_ERROR);
        }
    }
}