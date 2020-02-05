package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.common.ErrorMessage;
import duke.common.Message;
import duke.task.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        if (index < 1 || index > tasks.getLength()) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }
        
        tasks.markDone(index);
        storage.save(tasks);
        
        String output = Message.MARK_DONE + "\n"
                + Message.DIVIDER + "\n"
                + tasks.getTask(index) + "\n"
                + Message.DIVIDER + "\n";
        ui.showMessage(output);
    }
}


