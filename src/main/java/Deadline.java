/**
 * This class is the child class of the abstract class Task. It
 * contains a description and a boolean to mark if it has
 * been done.
 */

public class Deadline extends Task {

    private String date;

    /**
     * Default Constructor for Deadline Class.
     * @param description is a String that describes the task that is to be done.
     * @param date is the date of the deadline for the task
     * */
    public Deadline (String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for Deadline Class.
     * @param description is a String that describes the task that is to be done.
     * @param done is used when reading from the saved file.
     * @param date is the date that was recorded in the saved file.
     * */
    public Deadline (String description, boolean done, String date) {
        super(description);
        this.isDone = done;
        this.date = date;
    }

    /**
     * Converts the Object into the output string to the user.
     * @return the string to the user.
     * */
    public String toString(){
        return "[D][" + getStatusIcon() + "] " + getDescription() + "(by: " + this.date + ")";
    }

    public String toParser(){
        return "D /" + getStatusIcon() + "/" + this.description + "/" + this.date;
    }
}