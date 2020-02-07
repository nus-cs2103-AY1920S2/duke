package duke.command;

/*
 * ArchiveDeleteCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 07 Feb 2020
 *
 */

import duke.util.ArchiveList;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;

/**
 * <p>ArchiveDeleteCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to deleting the archived tasks.</p>
 * @author Mario Lorenzo
 */

public class ArchiveDeleteCommand extends Command {
    private int index;

    /**
     * Constructs an ArchiveDeleteCommand instance.
     * @param index The index of the archived tasks.
     */

    public ArchiveDeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the archive-delete command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @param archiveList The list of archived tasks.
     * @param archiveStorage The storage of the archive.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage) {
        return archiveList.deleteArchivedTask(index, archiveStorage);
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
