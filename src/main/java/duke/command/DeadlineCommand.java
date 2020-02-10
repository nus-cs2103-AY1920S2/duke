package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline command issued by the user.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Constructs the deadline command.
     *
     * @param input User's input to specify the description and deadline of the task.
     * @throws DukeException Thrown when user's input is incomplete or in incorrect format.
     */
    public DeadlineCommand(String input) throws DukeException {
        String[] taskDetails;
        try {
            String[] inputs = input.trim().split(" ", 2);
            taskDetails = inputs[1].split(" /by ");
            if (taskDetails[0].trim().isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            description = taskDetails[0];
            by = LocalDate.parse(taskDetails[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please provide a date using ' /by ' with the format yyyy-mm-dd..");
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
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        assert (size + 1) == tasks.size() : "Deadline command error";
        storage.writeToFile("D | 0 | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n", true);
        return "Got it. I've added this task:\n    "
                + deadline + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
