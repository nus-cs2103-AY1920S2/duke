package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.List;

public class TodoCommand extends Command {
    public TodoCommand(CommandType type, List<String> details) {
        super(type, details);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task task = new Todo(details.get(1));
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.replyAdded(taskList.getAmountOfTask(), task);
    }
}
