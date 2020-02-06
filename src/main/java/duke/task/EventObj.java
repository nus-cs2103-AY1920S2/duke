/*
 * @author leslieharland
 */

package duke.task;

/**
 * The Class EventObj has datetime property in addition to properties of a normal task.
 */
public class EventObj extends Task {

    /**
     * Instantiates a new event obj.
     *
     * @param description the description
     * @param datetime    the datetime
     */
    public EventObj(String description, String datetime) {
        super(description, datetime);
        type = "E";
    }

    /**
     * Parses the event.
     *
     * @param taskString the task string
     * @return the event obj
     */
    public static EventObj parse(String taskString) {
        String[] parts = taskString.split("\\|");
        String desc = parts[2];
        EventObj t = new EventObj(desc, "");
        if (parts.length > 3) {
            t = new EventObj(desc,
                    String.join("", java.util.Arrays.stream(parts).skip(3).toArray(String[]::new)));
        }
        t.isDone = parts[1].trim().equals("1");

        return t;
    }

    /**
     * Returns the task string e.g. Project meeting (at: 20 May 2020)
     *
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString() + "(at: " + printDateTime() + ")";
    }

    /**
     * Prints the event.
     *
     * @return the string
     */
    @Override
    public String print() {
        return getType() + " | " + (isDone ? String.valueOf(1)
                : String.valueOf(0)) + " | " + getDescription()
                + (!userKeyedDateString.equals("") ? " | " + printDateTime() : "");
    }
}
