package duke.pack;

public class Deadline extends Task {
    protected String by;

    /**
     * creates a deadline type of task
     * @param description task to be done
     * @param by date and time the task is due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatForFile() {
        String type = "D";
        String done;
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }

        return type + " | " + done + " | " + description + " | " + by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
