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
     * Parses the.
     *
     * @param taskString the task string
     * @return the deadline
     */
    public static Deadline parse(String taskString) {
        assert(!taskString.isEmpty());
        String[] parts = taskString.split("\\|");
        String desc = parts[2];
        Deadline t = new Deadline(desc, "");
        if (parts.length > 3) {
            t = new Deadline(desc, parts[3]);
        }
        t.isDone = parts[1].trim().equals("1");

        return t;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString() + "(by: " + printDateTime() + ")";
    }

    /**
     * Prints the.
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
