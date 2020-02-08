package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Updates a task in the TaskList.
 */
public class UpdateCommand extends Command {
    int index;
    String details;

    /**
     * Constructs an UpdateCommand with the specified index and details.
     * @param index The index of the task to be updated.
     * @param details The details of the update.
     */
    public UpdateCommand(int index, String details) {
        this.index = index;
        this.details = details;
    }

    /**
     * Updates the task at the specified index in the TaskList and returns an acknowledgement message.
     * @param tasks The TaskList containing the tasks.
     * @param ui The Ui that interacts with the user.
     * @param storage The Storage to load and save tasks into the data file.
     * @return A string with the message to be printed.
     * @throws DukeException If the details is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(index);
        if (task instanceof Todo) {
            throw new DukeException("Todo cannot be updated.");
        }
        if (details.trim().equals("")) {
            throw new DukeException("The details of the task to update cannot be empty.");
        }
        int dateIndex = details.indexOf("/d ");
        int timeIndex = details.indexOf("/t ");
        if ((dateIndex != 0 && timeIndex != 0)) {
            throw new DukeException("Invalid details format. "
                    + "Use d/ to indicate the new date and t/ to indicate the new time.");
        }

        String date = null;
        String time = null;
        if (dateIndex != -1 && timeIndex != -1) {
            date = details.substring(dateIndex + 3, timeIndex).trim();
            time = details.substring(timeIndex + 3).trim();
        } else if (dateIndex != -1) {
            date = details.substring(dateIndex + 3).trim();
        } else {
            time = details.substring(timeIndex + 3).trim();
        }

        updateTask(task, date, time);
        return ui.showToUser(Ui.MESSAGE_UPDATE, Ui.INDENT + task);
    }

    /**
     * Updates the task.
     * @param task The task to be updated.
     * @param date The updated date.
     * @param time The updated time.
     * @throws DukeException If date or time format is incorrect.
     */
    private void updateTask(Task task, String date, String time) throws DukeException {
        try {
            LocalDate newDate;
            LocalTime newTime;
            if (task instanceof Deadline) {
                if (date != null) {
                    newDate = LocalDate.parse(date);
                    ((Deadline) task).setDate(newDate);
                }
                if (time != null) {
                    newTime = LocalTime.parse(time);
                    ((Deadline) task).setTime(newTime);
                }
            } else {
                LocalTime newEndTime;
                if (date != null) {
                    newDate = LocalDate.parse(date);
                    ((Event) task).setDate(newDate);
                }
                if (time != null) {
                    String[] timeArray = time.split("-");
                    newTime = LocalTime.parse(timeArray[0]);
                    newEndTime = LocalTime.parse(timeArray[1]);
                    ((Event) task).setStartTime(newTime);
                    ((Event) task).setEndTime(newEndTime);
                }
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Incorrect date or time format. "
                    + "Format required: yyyy-mm-dd for date and hh:mm for time");
        }
    }
}
