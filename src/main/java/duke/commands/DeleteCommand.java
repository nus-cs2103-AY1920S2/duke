package duke.commands;

import duke.exceptions.DukeBadIndexException;
import duke.exceptions.DukeWriteFailException;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class DeleteCommand extends Command {
    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes 'delete' command.
     * @param taskList task list in running program.
     * @param ui ui handling running program.
     * @param storage storage handling running program.
     * @throws DukeWriteFailException in the event that new task cannot be written back to file.
     * @throws DukeBadIndexException if index is <=0 or greater than the number of tasks in the task list.
     */
    public String execute(
            TaskList taskList, Ui ui, Storage storage) throws DukeBadIndexException, DukeWriteFailException {
        if (!(index > taskList.numTasks())) {
            String t = taskList.getList().get(index - 1).toString();
            taskList.deleteTask(index);
            storage.write(taskList);
            return Ui.setBorder("OK, I've removed the task:\n\n" + t + "\n\n"
                    + "\tYou now have " + (taskList.getList().size()) + " task(s) on your list.");
        } else {
            throw new DukeBadIndexException(index);
        }
    }

    /**
     * Compares and checks if commands are equal.
     * @param command object to compare command to.
     * @return boolean stating if this command and command are equal.
     */
    @Override
    public boolean equals(Object command) {
        if (command instanceof DeleteCommand) {
            return this.index == ((DeleteCommand) command).index;
        } else {
            return false;
        }
    }

}
