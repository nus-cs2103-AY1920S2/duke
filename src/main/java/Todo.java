/**
 * This class is the child class of the abstract class Task. It
 * contains a description and a boolean to mark if it has
 * been done.
 */

public class Todo extends Task {

    /**
     * Default Constructor for Todo Class.
     * @param description is a String that describes the task that is to be done.
     * */
    public Todo (String description) {
        super(description);
    }

    /**
     * Constructor for Todo Class.
     * @param description is a String that describes the task that is to be done.
     * @param done is used when reading from the saved file.
     * */
    public Todo (String description, boolean done) {
        super(description);
        this.isDone = done;
    }

    /**
     * Converts the Object into the output string to the user.
     * @return the string to the user.
     * */
    public String toString() {
        return ("[T][" + getStatusIcon() + "] " + getDescription());
    }

    public String toParser() {
        return "T /" + getStatusIcon() + "/" + this.description;
    }
}
