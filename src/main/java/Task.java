import java.time.format.DateTimeFormatter;

/**
 * The class that all the tasks inherit from
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static DateTimeFormatter parser = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy ha");

    /**
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return Y or N depending on whether isDone is true or false
     */
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    /**
     * changes isDone to true
     */
    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * @return String to be output to the user
     */
    @Override
    public String toString() { return "[" + getStatusIcon() + "] " + this.description; }

    /**
     * @return String for the file format
     */
    public abstract String fileString();
}