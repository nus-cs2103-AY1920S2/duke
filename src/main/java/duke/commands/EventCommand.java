package duke.commands;

import duke.exceptions.DukeDuplicateTaskException;
import duke.exceptions.DukeWriteFailException;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command 'event'.
 */
public class EventCommand extends Command {
    protected String taskName;
    protected String dateTime;

    /**
     * Constructor for EventCommand.
     * @param taskName name of event.
     * @param dateTime date and time of event occurrence.
     */
    public EventCommand(String taskName, String dateTime) {
        assert !taskName.isEmpty();
        this.taskName = taskName;
        this.dateTime = dateTime;
    }

    /**
     * Executes 'event' command.
     * @param taskList task list in running program.
     * @param ui ui handling running program.
     * @param storage storage handling running program.
     * @throws DukeWriteFailException if the program fails to write the modified data to the storage file.
     */
    public String execute(
            TaskList taskList, Ui ui, Storage storage) throws DukeWriteFailException, DukeDuplicateTaskException {
        Event t = new Event(taskName, dateTime);
        if (!taskList.isDuplicate(t)) {
            taskList.addTask(t);
            storage.write(taskList);
            return Ui.setBorder("ADDED : \n\n" + t.toString() + "\n\n"
                    + "\tYou now have " + taskList.numTasks() + " item(s) on your list.");
        } else {
            throw new DukeDuplicateTaskException(t);
        }
    }

    /**
     * Compares and checks if commands are equal.
     * @param command object to compare command to.
     * @return boolean stating if this command and command are equal.
     */
    @Override
    public boolean equals(Object command) {
        if (command instanceof EventCommand) {
            if (((EventCommand) command).taskName.equals(this.taskName)) {
                return ((EventCommand) command).dateTime.equals(this.dateTime);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
