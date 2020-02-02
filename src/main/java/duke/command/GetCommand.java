package duke.command;

import duke.Storage;
import duke.TaskList;
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
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @return A string with the message to be printed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.find(date);
        if (filteredTasks.isEmpty()) {
            return ui.showToUser("There are no matching tasks in your list.");
        } else {
            return ui.showToUser("Here are the matching tasks in your list:", filteredTasks.toString());
        }
    }
}
