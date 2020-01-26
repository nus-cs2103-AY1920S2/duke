package tasks;

import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Task object, for deadlines. Contains methods pertaining to a deadline task.
 */
public class DeadlineTask extends Task {

    private String deadline;
    private LocalDateTime parsedDeadline;

    /**
     * Constructor of a deadline task.
     * @param description Description of the task.
     * @param deadline When the task has to be done by. Follows the format "dd/mm/yyyy HHmm".
     * @throws DukeException For parseDeadline method.
     */
    public DeadlineTask(String description, String deadline) throws DukeException {
        super(description);
        this.deadline = deadline;
        parseDeadline(deadline);
    }

    /**
     * Processes the String entered for a deadline into a LocalDate object, and saves it.
     * @param deadline Deadline in String form.
     * @throws DukeException Throws an exception when the deadline format is not followed correctly.
     */
    public void parseDeadline(String deadline) throws DukeException{
        try {
            this.parsedDeadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch(Exception e) {
            throw new DukeException("You've entered the deadline date incorrectly! Please use \"dd/mm/yyyy HHmm\" for" +
                    " your dates, e.g. 05/12/2020 1800");
        }
    }

    /**
     * Returns a parsed deadline.
     * @return Object containing the parsed deadline.
     */
    public LocalDateTime getParsedDeadline() {
        return parsedDeadline;
    }

    /**
     * Returns the String of the deadline.
     * @return Deadline String.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns a String of the task with the type of task and date.
     * @return Formatted String of a deadline task.
     */
    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(),
                parsedDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
