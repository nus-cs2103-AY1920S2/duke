package lcduke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Ths creates an Event object.
 */

public class Event extends Task {
    protected LocalDate taskDate;

    /** This is the constructor to create the Event Object.
     *
     * @param description Description of user's input.
     * @param at Deadline of user's task.
     */
    public Event(String description, String at) {
        super(description);
        if(at.contains("/")){
            at = at.replaceAll("/", "-");
        }
        this.taskDate = LocalDate.parse(at);
    }

    /** This prints the response after adding an Event object to task list.
     */
    public String printInit(){
        String response;
        response = "    ____________________________________________________________"
                + "     Got it. I've added this task: "
                + "       " + this.toString()
                + "     Now you have " + taskNo + " tasks in the list."
                + "    ____________________________________________________________\n";
        return response;
    }

    /** This generates the description in the list.
     * @return the description in the list
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
