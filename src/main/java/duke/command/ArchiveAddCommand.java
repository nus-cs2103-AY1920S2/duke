package duke.command;

import duke.util.ArchiveList;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;

public class ArchiveAddCommand extends Command {
    private int index;

    /**
     * Constructs an ArchiveAddCommand instance.
     * @param index The index of the task that is wanted to be archived.
     */

    public ArchiveAddCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the archive-add command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @param archiveList The list of archived tasks.
     * @param archiveStorage The storage of the archive.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage) {
        Task task = taskList.getTask(this.index);
        taskList.deleteTask(this.index, storage);
        return archiveList.archiveTask(task,archiveStorage);
    }

    /**
     * Returns a boolean value of whether the Command is a ByeCommand instance.
     * @return the boolean value of whether the instance is a ByeCommand.
     */

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
