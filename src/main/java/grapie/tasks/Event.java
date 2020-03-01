package grapie.tasks;

import grapie.exceptions.GrapieExceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String time;
    private LocalDate localDate;

    /**
     * Constructor for Grapie.Tasks.Event object.
     *
     * @param description Grapie.Tasks.Event's description.
     * @param time The time and date for the event.
     * @throws GrapieExceptions Throws error for incorrect formatting.
     */
    public Event(String description, String time) throws GrapieExceptions {
        super(description);

        time = time.trim();

        String[] splittedToCheckIfValid = time.split("-");
        if (splittedToCheckIfValid.length == 3 && time.length() == 10) {
            localDate = LocalDate.parse(time);
        } else {
            throw new GrapieExceptions("OOPS!!! Please format your date like this: YYYY-MM-DD TTTT");
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
