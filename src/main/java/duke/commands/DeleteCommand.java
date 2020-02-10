package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Deletes a task from list of current tasks
     * @param tasks List of current tasks
     * @param ui User interface used to reply user
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        if (command.equals("delete")) {
            throw new DukeException("Please enter a task number for deletion.");
        }

        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        String deleted = tasks.deleteTask(ui, storage, taskNumber);
        return "This task has been deleted: \n" + deleted;
    }
}
