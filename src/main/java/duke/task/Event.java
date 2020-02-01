package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that extends Task, by adding a new parameter called "at", to dictate when the event is happening
 */
public class Event extends Task {

    /**
     * Variable to store when the Event is happening at
     */
    public LocalDateTime at;

    /**
     * Constructor for Event object, specifying the description and datetime at which the event is happening
     * @param description Description of the Event
     * @param at String representation of Date & Time at which the event is happening
     */
    public Event(String description, String at) {
        super(description);
        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime outputDT = LocalDateTime.parse(at, inputDTF);
        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
        this.at = LocalDateTime.parse(outputDT.format(outputDTF), DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
    }

    /**
     * Returns a String representation of the Task object
     * @return a String representation of the Task object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a")) + ")";
    }
}