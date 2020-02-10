package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;

import task.Event;

import java.time.LocalDateTime;

/**
 * A command object for adding event tasks to the list.
 */
public class AddEventCommand extends AddCommand {
    private final LocalDateTime date;

    /**
     * Constructs a command object to add event task to the list.
     * @param taskName The name of the event task to be added.
     * @param date The date of the event task to be added.
     */
    public AddEventCommand(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Executes the command to add event tasks to the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Event newTask = new Event(this.taskName, this.date);
            tasks.add(newTask);
            storage.updateDrive(newTask);
            return ui.showTask(newTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return ui.showException(new DukeException(
                    "☹ OOPS!!! The description of an event cannot be empty."));
        } catch (DukeException de) {
            return ui.showException(de);
        }
    }
}
