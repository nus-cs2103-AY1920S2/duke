package seedu.java.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class only works with a single date and 2 timings.
 */
public class Event extends Task {
    private String eventName;
    private String timing;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Event.
     * @param eventName name of the event.
     * @param timing includes date and time.
     */
    public Event(String eventName, String timing) throws DateTimeParseException {
        super(eventName);
        this.timing = timing;
        convertDateAndTiming();
    }

    /**
     * Constructor for Event. Intended for Storage.
     * @param eventName name of the event.
     * @param timing includes date and time.
     * @param bool whether the event is completed or not.
     */
    public Event(String eventName, String timing, boolean bool) throws DateTimeParseException {
        super(eventName, bool);
        this.timing = timing;
        convertDateAndTiming();
    }

    public String getDateAndTiming() {
        return timing;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    public String getStartTime() {
        return this.startTime.format(DateTimeFormatter.ofPattern("HH mm"));
    }


    /**
     * Converts String into LocalDate & Time.
     */
    private void convertDateAndTiming() throws DateTimeParseException {
        String[] timingAsArray  = timing.split(" ");
        String dateAsString = timingAsArray[1];
        String startTimeAsString = timingAsArray[2].split("-")[0];
        String endTimeAsString = timingAsArray[2].split("-")[1];
        this.date = LocalDate.parse(dateAsString);
        this.startTime = LocalTime.parse(startTimeAsString);
        this.endTime = LocalTime.parse(endTimeAsString);
    }


    @Override
    public String toString() {
        return ". " + "[E] " + complete + task + "(" + timing + ")";
    }
}