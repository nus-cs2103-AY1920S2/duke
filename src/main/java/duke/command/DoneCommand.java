package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

public class DoneCommand extends Command {

    public DoneCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    @Override
    public void execute (TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            int num = Integer.parseInt(details.get(1));
            taskList.markDone(num);
            storage.saveTasks(taskList);
            ui.replyDone(taskList.getTask(num));
        } catch(NumberFormatException e) {
            throw new DukeException("Please input in this format: done [number]");
        }
    }
}
