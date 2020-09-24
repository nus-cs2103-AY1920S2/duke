package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TasksList;

public class DeleteCommand extends Command {
    String description;
    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException {
        Task task = tasksList.delete(description);
        ui.printSuccessfulRemoveEntry(tasksList, task);
        storage.saveTasksList(tasksList);
    }
}
