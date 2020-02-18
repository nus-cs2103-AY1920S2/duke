package lcduke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Ths creates a Deadline object.
 */
public class Deadline extends Task {
    private LocalDateTime taskDate;

    /** This is the constructor to create the Deadline Object.
     *
     * @param description Description of user's input.
     * @param by Deadline of user's task.
     */
    protected Deadline(String description, String by) {
        super(description);
        by = by.replace(" ", "T");
        taskDate = LocalDateTime.parse(by);
    }

    /** This prints the response after adding a Deadline object to task list.
     */
    protected String printInit() {
        String response;
        response = "     Got it. I've added this task: "
                + "       " + this.toString()
                + "     Now you have " + taskNo + " tasks in the list.";
        return response;
    }

    /** This generates the description in the list.
     * @return the description in the list
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:MM")) + ")";
    }
}
