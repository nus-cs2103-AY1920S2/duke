package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

import task.Deadline;

import java.time.LocalDateTime;

/**
 * A command object for adding deadline tasks to the list.
 */
public class AddDeadlineCommand extends AddCommand {
    private final LocalDateTime date;

    /**
     * Constructs a command object to add deadline task to the list.
     * @param taskName The name of the deadline task to be added.
     * @param date The date of the deadline task to be added.
     */
    public AddDeadlineCommand(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Executes the command to add deadline tasks to the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Deadline newTask = new Deadline(this.taskName, this.date);
            tasks.add(newTask);
            storage.updateDrive(newTask);
            return ui.showTask(newTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return ui.showException(new DukeException(
                    "☹ OOPS!!! The description of a deadline cannot be empty."));
        } catch (DukeException de) {
            return ui.showException(de);
        }
    }
}
