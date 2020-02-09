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
        return "[T]" + super.toString();
    }
}
