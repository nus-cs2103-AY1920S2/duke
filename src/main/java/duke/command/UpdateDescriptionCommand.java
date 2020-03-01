package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.core.Message;
import duke.task.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskIndexException;

public class UpdateDescriptionCommand extends Command {
    public UpdateDescriptionCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws TaskIndexException, InvalidCommandException {
        try {
            String[] split = this.input.split(" ");
            int idx = Integer.parseInt(split[2]);
            storage.updateDescription(idx, input);
            return tasks.updateDescription(idx - 1, input);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.INDEX_ERROR + "\n" + Message.UPDATE_DESC_INDEX_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.UPDATE_DESC_INDEX_ERROR);
        } catch (Exception e) {
            throw new InvalidCommandException(Message.UPDATE_GENERAL_ERROR);
        }
    }
}