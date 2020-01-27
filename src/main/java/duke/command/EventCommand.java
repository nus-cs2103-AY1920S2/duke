package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

public class EventCommand extends Command {
    public EventCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task task = new Event(details.get(1), details.get(2));
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.replyAdded(taskList.getAmountOfTask(), task);
    }
}
