package command;

import common.Storage;
import task.TaskList;
import exception.DukeException;
import ui.TextUi;

/**
 * Represents a command that show reminders of undone tasks.
 */
public class ReminderCommand extends Command {

    protected int filter = -1;

    public ReminderCommand() {
        super();
    }

    public ReminderCommand(int filter) {
        super();
        this.filter = filter;
    }

    /**
     * Executes the "showing reminders" type of command.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return the execution result in terms of a string
     * @throws DukeException
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        assert this.filter == -1 || this.filter == 1 || this.filter == 2 || this.filter == 3 : "Wrong parameter.";
        if (this.filter == 1) {
            return textUi.remindTasks(tasks, 'T');
        } else if(this.filter == 2) {
            return textUi.remindTasks(tasks, 'E');
        } else if(this.filter == 3) {
            return textUi.remindTasks(tasks, 'D');
        } else {
            return textUi.remindTasks(tasks, 'A');
        }
    }

    /**
     * Returns whether the current command is an exit command.
     *
     * @return a boolean value representing the property of the current command
     */
    public boolean isExit() {
        return false;
    }
}
