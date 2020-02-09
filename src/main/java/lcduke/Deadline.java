package lcduke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Ths creates a Deadline object.
 */

public class Deadline extends Task {
    protected LocalDate taskDate;

    /** This is the constructor to create the Deadline Object.
     *
     * @param description Description of user's input.
     * @param by Deadline of user's task.
     */
    public Deadline(String description, String by) {
        super(description);
        if(by.contains("/")){
            by = by.replaceAll("/", "-");
        }
        this.taskDate = LocalDate.parse(by);
    }

    /** This prints the response after adding a Deadline object to task list.
     */
    public void printInit(){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + this.toString());
        System.out.println("     Now you have " + taskNo + " tasks in the list.");
        System.out.println("    ____________________________________________________________\n");
    }

    /** This generates the description in the list.
     * @return the description in the list
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
