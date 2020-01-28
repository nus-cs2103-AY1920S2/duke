package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;

/**
 * Gets tasks from the TaskList.
 */
public class GetCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a GetCommand with the specified date.
     * @param date The date for the command.
     */
    public GetCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets tasks on the specified date and prints the tasks.
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if ((task instanceof Deadline && ((Deadline)task).getDate().equals(date))
                    || (task instanceof Event && ((Event)task).getDate().equals(date))) {
                filteredTasks.add(task);
            }
        }
        ui.showGetTasks(filteredTasks, date);
    }
}
