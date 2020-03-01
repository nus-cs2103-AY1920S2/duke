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

public class UpdateTimeCommand extends Command {
    public UpdateTimeCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException, InvalidTimeFormatException, InvalidCommandException {
        try {
            String[] split = this.input.split(" ");
            int idx = Integer.parseInt(split[2]);

            Task task = tasks.getTask(idx - 1);
            if (task instanceof Todo) {
                throw new InvalidCommandException(Message.TODO_TIME_ERROR);
            }

            storage.updateTime(idx, input);
            return tasks.updateTime(idx - 1, input);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.INDEX_ERROR + "\n" + Message.UPDATE_TIME_INDEX_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.UPDATE_TIME_INDEX_ERROR);
        } 
    }
}