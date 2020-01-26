package duke.task;

public class Deadline extends Task {

    /**
     * Constructor for duke.task.Deadline
     * @param description description of duke.task.Deadline
     */
    public Deadline(String description, String due) {
        super(description);
        period = due;
        type = "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period + ")";
    }
}
