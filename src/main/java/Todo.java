/**
 * CS2103 Individual Project.
 * The To do class extends the Task class, it encapsulates all the information pertaining
 * to to do objects.
 * @author Chiang Shun De
 */
public class Todo extends Task {

    /**
     * A constructor method for the to do class.
     * @param description A string representation of the description of the to do object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the to do object.
     * @return the String representation of the to do object.
     */
    @Override
    public String toString() {
        return "     [T]" + this.getIcon() + " " + this.description;
    }
}
