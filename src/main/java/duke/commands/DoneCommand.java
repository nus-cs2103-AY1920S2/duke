package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class DoneCommand extends Command {

    protected String command;

    public DoneCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Marks a particular task as done
     * @param tasks List of current tasks
     * @param ui User interface used to reply user
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (command.equals("done")) {
            throw new DukeException("Please enter a task number for completion.");
        }

        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        String completed = tasks.completeTask(ui, storage, taskNumber);
        return "This task has been marked as completed: \n" + completed;
    }
}
