package duke.task;

import duke.Duke;
import duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1>Event Class</h1>
 * A subclass of Task class. Record the description and duration of the event task.
 *
 * @author Eng Xuan En
 */
public class Event extends Task {
    protected LocalDateTime duration;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * \
     * Class constructor of Event.
     *
     * @param description Description of the event
     * @param duration    Duration of the event
     * @throws DukeException Occur only when date and time format is wrong or invalid.
     */
    public Event(String description, String duration) throws DukeException {
        super(description);
        type = "event";

        try {
            retrieveDateAndTime(duration);
        } catch (DateTimeException e) {
            throw new DukeException("Please give a valid duration in yyyy-mm-dd HHmm format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please input time in HHmm format at the end.");
        }
    }

    /**
     * Retrieve the date and time from the input given.
     *
     * @param input Input from user
     * @throws DateTimeException              Occur when date or time are invalid.
     * @throws ArrayIndexOutOfBoundsException Occur when time is not provided.
     */
    private void retrieveDateAndTime(String input) throws DateTimeException, ArrayIndexOutOfBoundsException {
        String[] data = input.split(" ");
        this.period = LocalDate.parse(data[0]);
        this.duration = addTimeIntoDate(data[1]);
    }

    /**
     * Add Time into the date.
     *
     * @param time Time to be added.
     * @return LocalDateTime object with the date and time stored.
     * @throws DateTimeException Occur when time is invalid.
     */
    private LocalDateTime addTimeIntoDate(String time) throws DateTimeException {
        return period.atTime(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)));
    }

    /**
     * Get duration in "yyyy-mm-dd HHmm" format.
     *
     * @return duration in "yyyy-mm-dd HHmm"
     */
    @Override
    public String getPeriod() {
        return duration.format(storeFormatter);
    }

    /**
     * Get string in [E][Y or N] {description of the task} (at: {duration of the task}) format.
     *
     * @return String in certain format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration.format(outputFormatter) + ")";
    }
}
