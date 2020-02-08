package duke.command;

import duke.util.ArchiveList;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * ArchiveListCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 07 Feb 2020
 *
 */

/**
 * <p>ArchiveListCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to list the archived tasks.</p>
 * @author Mario Lorenzo
 */

public class ArchiveListCommand extends Command {

    /**
     * Constructs an ArchiveListCommand instance.
     */

    public ArchiveListCommand() {

    }

    /**
     * Executes the archive-list command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @param archiveList The list of archived tasks.
     * @param archiveStorage The storage of the archive.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage,
                          NoteList noteList, NoteStorage noteStorage) {
        return archiveList.listArchivedTasks();
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
