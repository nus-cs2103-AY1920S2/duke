package duke.command;

import duke.util.ArchiveList;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * ByeCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 06 Feb 2020
 *
 */

/**
 * ByeCommand class extends a Command abstract class
 * and it represents the terminating command when a user
 * wants to end using Duke.
 * @author Mario Lorenzo
 */

public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand instance.
     */

    public ByeCommand() {

    }

    /**
     * Executes the command by returning the exit message.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @param archiveList The list of archived tasks.
     * @param archiveStorage The storage of the archive.
     * @param noteList The list of notes.
     * @param noteStorage The storage of the notes.
     * @return The String representing the outcome of the execution.
     */

    @Override
    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage,
                          NoteList noteList, NoteStorage noteStorage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a boolean value of whether the Command is a ByeCommand instance.
     * @return the boolean value of whether the instance is a ByeCommand.
     */

    @Override
    public boolean isByeCommand() {
        return true;
    }
}
