package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Gets tasks from the TaskList.
 */
public class GetCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a GetCommand with the specified date.
     *
     * @param date The date for the command.
     */
    public GetCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets tasks on the specified date and returns a string representation the tasks.
     *
     * @param tasks The TaskList containing the tasks.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks) {
        TaskList filteredTasks = tasks.find(date);
        if (filteredTasks.isEmpty()) {
            return Ui.MESSAGE_NO_MATCHING_TASK;
        } else {
            return Ui.showToUser(Ui.MESSAGE_FIND, filteredTasks.toString());
        }
    }
}
