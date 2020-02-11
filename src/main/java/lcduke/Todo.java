package lcduke;

/** Ths creates a Todo object.
 */

public class Todo extends Task {

    /** This is the constructor to create the Todo Object.
     *
     * @param description Description of user's input.
     */
    public Todo(String description) {
        super(description);
    }

    /** This prints the response after adding a Todo object to task list.
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
        return "[T]" + super.toString();
    }
}
