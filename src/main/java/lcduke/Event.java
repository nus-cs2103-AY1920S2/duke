package lcduke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Ths creates an Event object.
 */
public class Event extends Task {
    private LocalDateTime taskDate;

    /** This is the constructor to create the Event Object.
     *
     * @param description Description of user's input.
     * @param at Deadline of user's task.
     */
    public Event(String description, String at) {
        super(description);
        at = at.replace(" ", "T");
        taskDate = LocalDateTime.parse(at);
    }

    /** This prints the response after adding an Event object to task list.
     */
    protected String printInit() {
        String response;
        response = "     Got it. I've added this task: \n"
                + "       " + this.toString() + "\n"
                + "     Now you have " + taskNo + " tasks in the list.\n";
        return response;
    }

    /** This generates the description in the list.
     * @return the description in the list
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:MM")) + ")";
    }
}
