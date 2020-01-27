package duke.task;

import duke.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime duration;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructor for duke.task.Event
     * @param description description of duke.task.Event
     */
    public Event(String description, String duration) throws DukeException{
        super(description);
        try {
            String[] date = duration.split(" ");
            period = LocalDate.parse(date[0]);
            this.duration = period.atTime(Integer.parseInt(date[1].substring(0, 2)),
                    Integer.parseInt(date[1].substring(2, 4)));
            type = "event";
        } catch(DateTimeParseException e) {
            throw new DukeException("Please give a valid duration in yyyy-mm-dd HHmm format.");
        } catch(DateTimeException e) {
            throw new DukeException("Please give a valid duration in yyyy-mm-dd HHmm format.");
        }
    }

    /**
     * Get duration
     * @return duration in "yyyy-mm-dd HHmm"
     */
    @Override
    public String getPeriod() {
        return duration.format(storeFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration.format(outputFormatter) + ")";
    }
}
