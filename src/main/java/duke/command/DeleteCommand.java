package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

public class DeleteCommand extends Command {

    public DeleteCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    @Override
    public void execute (TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            int num = Integer.parseInt(details.get(1));
            Task task = taskList.deleteTask(num);
            storage.saveTasks(taskList);
            ui.replyDelete(task);
        } catch(NumberFormatException e) {
            throw new DukeException("Please give a valid number in this format: delete [number]");
        }
    }
}
