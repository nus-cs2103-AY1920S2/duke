package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Event which extends from Task.
 * Handles all inputs which has "event..."
 */
public class Event extends Task {

    // for the date and time option
    private String at;

    // isDay if the user uses "../at Monday" instead of a standard yyyy-mm-dd format
    //private static boolean isDay = false;
    // Days array to determine if its a day or a yyyy-mm-dd format
    //String[] days;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     */
    public Event(String description) {

        super(description);
    }

    private String getAt() {
        return at;
    }

    /**
     *  Sets the date and time of the event task.
     *
     * @param s the s
     */
    public void setAt(String s) {
        this.at = super.set_by_at(s);
    }

    /**
     * Sets d1, the LocalDateTime for the Task.
     *
     * @throws DukeException the duke exception
     */
    public void setD1() throws DukeException {
        super.setD1(getAt());
    }

    // Covers exceptions caused by empty descriptions
    // Incorrect use of identifiers "../at" (More stringent check of formatting)
    // No deadline descriptors. Eg: "deadline /by Monday"
    // Same for TO-DO, duke.task.Deadline and duke.task.Event classes!
    /**
     * From the input given by the user, filter out the commands (Event)
     * And returns the description of the string.
     * @param s the s
     * @return String without "event"
     * @throws DukeException when the user enters an empty description or use the wrong event format.
     * Eg: use ../by instead of ../at
     */

    @Override
    public String format_tasks(String s) throws DukeException {

        String[] splited_string = getDescription().split("event");

        if(splited_string.length <1) {
            throw new DukeException("You cannot leave the description empty");
        } else {
            try{
                return s.substring(s.indexOf("at")).replaceAll("at ", "");

            } catch (Exception e) {
                throw new DukeException("Please use ../at instead of any other identifiers ");
            }
        }
    }

    /**
     * Gets description of the task. WWithout the date and time .
     *
     * @return the deadline task description
     */
    @Override
    public void setDescription(String s) throws DukeException {

        try {
            String event_task = s.substring(s.indexOf("event"), s.indexOf("/"));
            String event_name = event_task.replaceAll("event", "").trim();
            super.setDescription(event_name);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for an event ");
        }
    }

    /**
     * Gets task codes.
     *
     * @return the task codes
     */
    Task_Codes getTaskCodes() {
        return Task_Codes.E;
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.E + "]" + super.toString() + " (at: " +
                // The format is to change the formatting patterns (Dec 2 2019 OR 2/12/2019)
                d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
