/*
 * @author leslieharland
 */

package duke.task;

import duke.command.Operation;

import java.time.format.DateTimeFormatter;

/**
 * The Class Task.
 */
public abstract class Task implements TaskPrintable, Parseable {

    protected String description;

    protected boolean isDone;

    protected String type;

    protected String userKeyedDateString;

    protected java.time.LocalDate datetime;

    /**
     * Instantiates a new task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
=======
     * This constructor is used to retain most useful properties needed to create
     * task at its core.
     *
     * @param description the description
     */
    public Task(boolean isDone, String description, String type) {
        this.isDone = isDone;
        this.description = description;
        this.type = type;
    }

    /**
     * Instantiates a new task.
     *
     * @param description the description
     * @param datetime    the datetime
     */
    public Task(String description, String datetime) {
        this.userKeyedDateString = datetime;
        setDatetime(datetime);
        this.description = description;
    }

    /**
     * Instantiates a new task.
     *
     * @param isDone      the is done
     * @param description the description
     */
    private Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the datetime.
     *
     * @param dt the new datetime
     */
    protected void setDatetime(String dt) {
        dt = dt.replaceAll("\\s", "");
        try {
            java.time.format.DateTimeFormatter formatter
                    = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
            datetime = java.time.LocalDate.parse(dt, formatter);
        } catch (Exception ex) {
            try {
                java.time.format.DateTimeFormatter formatter
                        = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm");
                datetime = java.time.LocalDate.parse(dt, formatter);
            } catch (Exception ex2) {
                System.out.println();
            }

        }
    }

    /**
     * Prints the date time.
     *
     * @return the string
     */
    public String printDateTime() {
        DateTimeFormatter outputformatter
                = java.time.format.DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            String output = (datetime).format(outputformatter);
            return output;
        } catch (Exception ex) {
            return userKeyedDateString;
        }

    }

    /**
     * Gets the status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Checks if is done.
     *
     * @return true, if is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        if (type.equalsIgnoreCase(Operation.TODO.toString())) {
            this.type = "T";
        } else if (type.equalsIgnoreCase(Operation.DEADLINE.toString())) {
            this.type = "D";
        } else if (type.equalsIgnoreCase(Operation.EVENT.toString())) {
            this.type = "E";
        } else {
            this.type = "";
        }
    }

    /**
     * Mark as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "[" + getType() + "]"
                + " [" + getStatusIcon() + "]  " + getDescription();
    }

    /**
     * Prints the.
     *
     * @return the string
     */
    public String print() {
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0))
                + " | " + getDescription();
    }

}