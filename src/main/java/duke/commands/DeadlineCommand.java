package duke.commands;

import duke.exceptions.DukeDuplicateTaskException;
import duke.exceptions.DukeWriteFailException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command 'deadline'.
 */
public class DeadlineCommand extends Command {
    String taskName;
    String dateTime;

    /**
     * Creates a command to create a Deadline task.
     * @param taskName name of deadline.
     * @param dateTime date and time of deadline.
     */
    public DeadlineCommand(String taskName, String dateTime) {
        assert !taskName.isEmpty();
        this.taskName = taskName;
        this.dateTime = dateTime;
    }

    /**
     * Executes 'deadline' command.
     * @param taskList task list in running program.
     * @param ui ui handling running program.
     * @param storage storage handling running program.
     * @throws DukeWriteFailException in the event that new task cannot be written back to file.
     */
    public String execute(
            TaskList taskList, Ui ui, Storage storage) throws DukeWriteFailException, DukeDuplicateTaskException {
        Deadline t = new Deadline(taskName, dateTime);
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
        if (command instanceof DeadlineCommand) {
            if (((DeadlineCommand) command).taskName.equals(this.taskName)) {
                return ((DeadlineCommand) command).dateTime.equals(this.dateTime);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
