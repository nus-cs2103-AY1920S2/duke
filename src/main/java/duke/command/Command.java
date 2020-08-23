package duke.command;

import duke.util.ArchiveList;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * Command
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>Command abstract class defines behavior of commands
 * entered by client. A command have an execute method.</p>
 * @author Mario Lorenzo
 */

public abstract class Command {

    /**
     * Executes the command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @param archiveList The list of archived tasks.
     * @param archiveStorage The storage of the archive.
     * @param noteList The list of notes.
     * @param noteStorage The storage of the notes.
     * @return The String representing the outcome of the execution.
     */
    public abstract String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage,
                                   NoteList noteList, NoteStorage noteStorage);

    /**
     * Returns a boolean value of whether the Command is a ByeCommand instance.
     * @return the boolean value of whether the instance is a ByeCommand.
     */

    public abstract boolean isByeCommand();
}
