package duke.command;

import duke.util.ArchiveList;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Note;

/*
 * NoteAddCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>NoteAddCommand extends the Command abstract class
 * and it describes the behavior of a command regarding
 * to adding notes.</p>
 * @author Mario Lorenzo
 */

public class NoteAddCommand extends Command {
    private Note note;

    /**
     * Constructs a NoteAddCommand instance.
     * @param note The note that would like to be added to the list.
     */
    public NoteAddCommand(Note note) {
        this.note = note;
    }

    /**
     * Executes the command by adding the note to noteList
     * and write the details in the hard disk.
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
        return noteList.addNote(note, noteStorage);
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
