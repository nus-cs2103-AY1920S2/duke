package duke.task;

/**
 * Represents a deadline to meet that contains both a description and a deadline time.
 */
public class Deadline extends Task {
    /**
     * The time of the Deadline, as input by the user.
     */
    protected String by;

    /**
     * Constructor to create a Deadline task..
     * @param description The description of the Deadline task, as input by the user.
     * @param by the time of the Deadline task, as input by the user.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Takes in the arguments after the command keyword as input by the user.
     * Returns the Deadline task object, if successful, based on the input.
     * If incorrect arguments were specified, throws an IllegalArgumentException.
     * @param commandArgs the arguments after the command keyword as input by the user.
     * @return DeadlineTask object, if successful.
     * @throws IllegalArgumentException if user entered incorrect arguments.
     */
    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {
        String description = "";
        String by = "";

        int toggle = 0;
        for (int i = 0; i < commandArgs.length; i++) {
            if (commandArgs[i].equals("(by:")) {
                toggle = 1;
                continue;
            }
            // current token is part of description
            if (toggle == 0) {
                description += commandArgs[i] + " ";
            }
            // current token is part of by
            else if (toggle == 1) {
                by += commandArgs[i] + " ";
            }
        }

        by = by.trim();
        by = by.substring(0, by.length() - 1);

        return new Deadline(description.trim(), by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}