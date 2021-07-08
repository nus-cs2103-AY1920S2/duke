package duke.task;

import duke.exception.DukeException;

import java.time.format.DateTimeFormatter;

/**
 * The type Event which extends from Task.
 * Handles all inputs which has "event..."
 */
public class Event extends Task {

    // for the date and time option
    private String at;

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
     * Sets the date and time of the event task.
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

    /**
     * From the input given by the user, filter out the commands (Event)
     * And returns the description of the string.
     *
     * @param s the s
     * @return String without "event"
     * @throws DukeException when the user enters an empty description or use the wrong event format.
     */

    @Override
    public String formatTasks(String s) throws DukeException {

        String[] splitedString = getDescription().split("event");

        if (splitedString.length < 1) {
            throw new DukeException("You cannot leave the description empty");
        } else {
            try {
                return s.substring(s.indexOf("at")).replaceAll("at ", "");

            } catch (Exception e) {
                throw new DukeException("Please use ../at instead of any other identifiers ");
            }
        }
    }

    /**
     * Gets description of the task. WWithout the date and time .
     * return the deadline task description
     */
    @Override
    public void setDescription(String s) throws DukeException {

        try {
            String eventTask = s.substring(s.indexOf("event"), s.indexOf("/"));
            String eventName = eventTask.replaceAll("event", "").trim();
            super.setDescription(eventName);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for an event ");
        }
    }

    @Override
    public String toString() {
        // The format is to change the formatting patterns (Dec 2 2019 OR 2/12/2019)
        return " [" + TaskCode.E + "]" + super.toString() + " (at: "
                + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
