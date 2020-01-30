package duke.commands;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    int index = -1;

    public DeleteCommand(int index) {
        this.index = index;
    }

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException(ErrorCodes.INVALID_TASK_INDEX);
        } else {
            Task deletedTask = tasks.deleteTask(index);
            ui.dukePrompt(new String[]{"Aaaaand deleted! Don't kill me if it's the wrong one, boss",
                "\n",
                deletedTask.toString(),
                "\n",
                tasks.printTasksTotal()});
            storage.save(tasks);
        }
	}

	@Override
	public boolean isExit() {
		return false;
	}
}