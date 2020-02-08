package duke.command;

/*
 * NoteListCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * <p>NoteListCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to list the notes.</p>
 * @author Mario Lorenzo
 */

public class NoteListCommand extends Command {
    /**
     * Constructs a NoteListCommand instance.
     */

    public NoteListCommand() {

    }

    /**
     * Executes the list command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage, NoteList noteList, NoteStorage noteStorage) {
        return noteList.listNotes();
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
