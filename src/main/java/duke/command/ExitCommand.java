package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class ExitCommand extends Command {
    public ExitCommand(CommandType type) {
        super(type);
        isExit = true;
    }

    @Override
    public void execute (TaskList taskList, Storage storage, Ui ui) {
        ui.reply("Alright! See you next time!");
    }
}
