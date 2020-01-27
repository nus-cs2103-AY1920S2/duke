package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(CommandType type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.replyListing(taskList.getListing());
    }
}
