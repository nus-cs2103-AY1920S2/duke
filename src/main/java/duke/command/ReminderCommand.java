package duke.command;

import duke.util.ArchiveList;
import duke.util.Storage;
import duke.util.TaskList;

/*
 * ReminderCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 06 Feb 2020
 *
 */

/**
 * <p>ReminderCommand class extends the Command class, and it
 * describe the behavior of a reminder, where the top 3 nearest deadline
 * or event is being listed.</p>
 * @author Mario Lorenzo
 */

public class ReminderCommand extends Command {

    /**
     * Constructs a ReminderCommand instance.
     */

    public ReminderCommand() {

    }

    /**
     * Executes the reminder command.
     * @param taskList The list of the tasks.
     * @param storage The storage of the Duke.
     * @return The output String of the result.
     */

    @Override
    public String execute(TaskList taskList, Storage storage, ArchiveList archiveList, Storage archiveStorage) {
        return taskList.listReminder();
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
