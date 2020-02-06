/*
 * @author leslieharland
 */

package duke.task;

/**
 * The Class Deadline.
 */
public class Deadline extends Task {

    /**
     * Instantiates a new deadline.
     *
     * @param description the description
     * @param date        the date
     */
    public Deadline(String description, String date) {
        super(description, date);
        type = "D";
    }

    /**
     * Parses the deadline object from a task string stored in the file
     * e.g. D | 1 | Borrow book
     *
     * @param taskString the task string
     * @return the deadline
     */
    public static Deadline parse(String taskString) {
        assert (!taskString.isEmpty());
        String[] parts = taskString.split("\\|");
        String desc = parts[2];
        Deadline dl = new Deadline(desc, "");
        if (parts.length > 3) {
            dl = new Deadline(desc, parts[3]);
        }
        dl.isDone = parts[1].trim().equals("1");

        return dl;
    }

    /**
     * Returns the task string e.g. 1. Deadline (by: 20 May 2020)
     *
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + printDateTime() + ")";
    }

    /**
     * Similar to the toString method.
     *
     * @return the string
     */
    @Override
    public String print() {
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0))
                + " | " + getDescription() + (!userKeyedDateString.equals("") ? " | "
                + printDateTime() : "");
    }
}
