package resources;

import exceptions.DukeException;

import java.time.LocalDateTime;

/**
 * Resources.Event is a type of task that accepts /at statements only
 */
public class Event extends Task {

    private LocalDateTime endDate;

    /**
     * Instantiates a new Resources.Event.
     *
     * @param description the description
     * @throws DukeException the duke exception
     */
    public Event(String description)throws DukeException {
        super("default");


        String[] parsed = getParse(description);
        endDate = DateTimeHandler.getLocalDateTime(parsed[1]);
        super.resetDesc(parsed[0]);
    }

    /**
     * Parses the command for better execution
     *
     * @param desc the description
     * @throws DukeException the duke exception
     * @return A string array containing the parsed split desc and time
     */
    private String[] getParse(String desc) throws DukeException {
        if(!desc.contains("/at ")){
            throw new DukeException("Hello? Missing your /at command!");
        }
        String[] parsed = desc.split("/at ");
        return parsed;
    }


    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + DateTimeHandler.printsLocalDateTime(endDate) + ")";
    }


}
