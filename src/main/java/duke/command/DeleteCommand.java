package duke.command;

import duke.util.ArchiveList;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * DeleteCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>DeleteCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to the deletion of a task.</p>
 * @author Mario Lorenzo
 */

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs the DeleteCommand.
     * @param index The index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
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
        return taskList.deleteTask(this.index, storage);
    }

    /**
     * Returns a boolean value of whether the Command is a ByeCommand instance.
     * @return the boolean value of whether the instance is a ByeCommand.
     */

    @Override
    public boolean isByeCommand() {
        return false;
    }

    /**
     * Overrides the Object's equals method to compare
     * whether two DeleteCommand instances are equal.
     * @param object The object that wants to be compared.
     * @return The boolean whether it is equal or not.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof DeleteCommand) {
            DeleteCommand castedDeleteCommand = (DeleteCommand)object;
            return this.index == castedDeleteCommand.index;
        } else {
            return false;
        }
    }
}
