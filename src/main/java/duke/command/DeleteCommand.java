package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputTokens = this.command.split(" ");
        int deleteIndex;

        try {
            deleteIndex = Integer.parseInt(inputTokens[1]) - 1;
            Task deleteTask = tasks.delete(deleteIndex);
            ui.showDeleteTask(deleteTask, tasks.size());
            if (!storage.save(tasks)) {
                throw new DukeException("☹ OOPS!!! Failed to save list!");
            }
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! No such task index!");
        }
    }
}
