package duke.command;

import duke.DukeException;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event command issued by the user.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDate at;

    /**
     * Constructs the event command.
     *
     * @param input User's input to specify the description and date of the task.
     * @throws DukeException Thrown when user's input is incomplete or in incorrect format.
     */
    public EventCommand(String input) throws DukeException {
        String[] taskDetails;
        try {
            String[] inputs = input.trim().split(" ", 2);
            taskDetails = inputs[1].split(" /at ");
            if (taskDetails[0].trim().isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        try {
            description = taskDetails[0];
            at = LocalDate.parse(taskDetails[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please provide a date using ' /at ' with the format yyyy-mm-dd..");
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please provide a valid date with the format yyyy-mm-dd.");
        }
    }

    /**
     * Executes the command.
     *
     * @param storage Storage class for the command to write data.
     * @param tasks TaskList class for the command to insert the task.
     * @return Response to be displayed to the user.
     * @throws DukeException Thrown when there is an error writing file.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        int size = tasks.size();
        Task event = new Event(description, at);
        tasks.add(event);
        assert (size + 1) == tasks.size() : "Event command error";
        storage.writeToFile("E | 0 | " + description + " | "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n", true);
        return "Got it. I've added this task:\n    "
                + event + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
