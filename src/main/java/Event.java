/**
 * This class is the child class of the abstract class Task. It
 * contains a description and a boolean to mark if it has
 * been done.
 */

public class Event extends Task {

    private String date;

    /**
     * Default Constructor for Deadline Class.
     * @param description is a String that describes the task that is to be done.
     * @param date is the date of the time of the event
     * */
    public Event (String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for Event Class.
     * @param description is a String that describes the task that is to be done.
     * @param done is used when reading from the saved file.
     * @param date is the date that was recorded in the saved file.
     * */
    public Event (String description, boolean done, String date) {
        super(description);
        this.isDone = done;
        this.date = date;
    }

    public String toString(){
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(at: " + this.date + ")";
    }

    public String toParser(){
        return "E /" + getStatusIcon() + "/" + this.description + "/" + this.date;
    }
}