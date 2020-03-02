package duke.task;

/**
 * Represents a Event that contains both a description and a event time.
 */
public class Event extends Task {

    /**
     * The time of the Deadline, as input by the user.
     */
    protected String at;

    /**
     * Constructor to create a Event task..
     * @param description The description of the Event task, as input by the user.
     * @param at the time of the Event task, as input by the user.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Takes in the arguments after the command keyword as input by the user.
     * Returns the Event task object, if successful, based on the input.
     * If incorrect arguments were specified, throws an IllegalArgumentException.
     * @param commandArgs the arguments after the command keyword as input by the user.
     * @return Event task object, if successful.
     * @throws IllegalArgumentException if user entered incorrect arguments.
     */
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        String at = "";

        int toggle = 0;
        for (int i = 0; i < commandArgs.length; i++) {
            if (commandArgs[i].equals("(at:")) {
                toggle = 1;
                continue;
            }
            // current token is part of description
            if (toggle == 0) {
                description += commandArgs[i] + " ";
            }
            // current token is part of by
            else if (toggle == 1) {
                at += commandArgs[i] + " ";
            }
        }

        at = at.trim();
        at = at.substring(0, at.length() - 1);

        return new Event(description.trim(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}