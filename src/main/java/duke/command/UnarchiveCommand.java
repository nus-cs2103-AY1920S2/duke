package duke.command;

import duke.util.ArchiveList;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;

/*
 * UnarchiveCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 13 Feb 2020
 *
 */

/**
 * <p>UnarchiveCommand extends the Command class which describes the behavior
 * of unarchiving tasks command.</p>
 * @author Mario Lorenzo
 */

public class UnarchiveCommand extends Command {
    private int index;

    /**
     * Constructs an ArchiveAddCommand instance.
     * @param index The index of the task that is wanted to be unarchived.
     */

    public UnarchiveCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unarchive command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @param archiveList The list of archived tasks.
     * @param archiveStorage The storage of the archive.
     * @param noteList The list of notes.
     * @param noteStorage The storage of the notes.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage,
                          NoteList noteList, NoteStorage noteStorage) {
        Task task = archiveList.getTask(this.index);
        archiveList.deleteArchivedTask(this.index, storage);
        taskList.addTask(task, storage);
        return "Done! I have successfully unarchived:\n" + task;
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
