import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task with event
 * Extends the Task class
 */
public class Event extends Task {

    protected LocalDate date;

    /**
     * Constructor for Event
     * @param description of the event
     * @param date to be completed
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Get the type of task
     * D for Deadline
     * T for To-do
     * E for Event
     * @return the type of task
     */
    public String getTaskType() {
        return "E";
    }

    public String getDate() {
        return this.date.toString();
    }
    public String formatDate() {
        DateTimeFormatter myformat = DateTimeFormatter.ofPattern("d MMM uuuu");
        String converted = this.date.format(myformat);
        return converted;
    }

    @Override
    public String toString() {
        String myword = "";
        myword = myword + "[" + this.getTaskType() + "]"
                + " [" + super.getStatusIcon() + "] " + super.description +
                " (" + this.formatDate() + ")";

        return myword;
    }
}
