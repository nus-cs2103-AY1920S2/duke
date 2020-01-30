package duke.commands;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int index = -1;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException(ErrorCodes.INVALID_TASK_INDEX);
        } else {
            Task currentTask = tasks.setDone(this.index);
            ui.dukePrompt(new String[]{"Got it boss! Just to confirm, this is the one I marked as done",
                "\n",
                currentTask.toString(),
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