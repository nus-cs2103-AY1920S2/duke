package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import duke.main.TaskList;
import duke.main.Ui;
import duke.main.Storage;
import duke.DukeException;
import duke.task.Event;

/**
 * Command that is executed when user inputs 'event'.
 */
public class EventCommand extends Command {
    private String[] eventDesc;

    public EventCommand(String[] eventDesc) {
        this.eventDesc = eventDesc;
    }

    /**
     * Executes the 'event' command and saves latest version of the task list.
     *
     * @param tasks Task list
     * @param ui Current user interface
     * @param storage Current storage
     * @return String representation of success message after an event is added to the task list
     * @throws DukeException If task list fails to save
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event newEvent;
        if (eventDesc.length == 2) {
            // Case: event <desc> /at <YYYY-MM-DD of event>
            newEvent = new Event(eventDesc[0], LocalDate.parse(eventDesc[1]));
        } else if (eventDesc.length == 3) {
            // Case: event <desc> /at <YYYY-MM-DD of event> <start HH:mm>
            newEvent = new Event(eventDesc[0], LocalDate.parse(eventDesc[1]),
                    LocalTime.parse(eventDesc[2]));
        } else {
            // Case: event <desc> /at <YYYY-MM-DD of event> <start HH:mm> to <end HH:mm>
            newEvent = new Event(eventDesc[0], LocalDate.parse(eventDesc[1]),
                    LocalTime.parse(eventDesc[2]), LocalTime.parse(eventDesc[3]));
        }
        tasks.addToTaskList(newEvent);
        storage.save(tasks.getTaskList());
        return ui.showSuccessMessage(newEvent.toString(), tasks.getTaskList().size());
    }

    /**
     * Updates main function if it should exit the programme or not.
     *
     * @return True if 'bye' command is called, false otherwise. In this case, false is returned.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
