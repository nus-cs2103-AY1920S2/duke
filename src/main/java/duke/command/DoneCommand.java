package duke.command;

import duke.core.Message;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexException;

/**
 * Represents a done task command.
 */
public class DoneCommand extends Command {
    /**
     * Constructs a fresh instance of a done command.
     * @param input The user input.
     */
    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Executes the Done command for the program to mark a task as done.
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
            throw new InvalidCommandException(Message.DONE_ERROR);
        }

        try {
            int idx = Integer.parseInt(split[1]);
            storage.doTask(idx);
            return tasks.doTask(idx - 1);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Message.DONE_ERROR);
        }
    }
}