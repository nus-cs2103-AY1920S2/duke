package duke.command;

import duke.core.Ui;
import duke.core.Message;
import duke.core.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTimeFormatException;
import duke.exception.TaskIndexException;

/**
 * Represents an update time command.
 */
public class UpdateTimeCommand extends Command {
    /**
     * Constructs a fresh instance of an update time command.
     * @param input The user input.
     */
    public UpdateTimeCommand(String input) {
        super(input);
    }

    /**
     * Executes the Update Time command for the program to update the time of a task.
     * @param tasks Tasklist for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke
     * @return A String containing the response from the executed method.
     * @throws InvalidCommandException An exception telling ifthe command is invalid.
     * @throws TaskIndexException An exception if the task index is invalid.
     * @throws InvalidTimeFormatException An exception if the time format is invalid.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws TaskIndexException, InvalidTimeFormatException, InvalidCommandException {
        int idx = 0;
        try {
            String[] split = this.input.split(" ");
            idx = Integer.parseInt(split[2]);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.UPDATE_TIME_ERROR);
        } 

        try {
            Task task = tasks.getTask(idx - 1);
            if (task instanceof Todo) {
                throw new InvalidCommandException(Message.TODO_TIME_ERROR);
            }

            storage.updateTime(idx, input);
            return tasks.updateTime(idx - 1, input);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.INDEX_ERROR);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        }
    }
}