package duke.pack;

/**
 * Represents a to-do type of task.
 */
public class Todo extends Task {
    protected String fullDesc;
    protected final String type = "T";
    protected String dateToCompare;

    /**
     * Creates a to-do type of task.
     * @param description task to be done
     * @param fullDesc the full description of the task
     */
    public Todo(String description, String fullDesc, String dateToCompare) {
        super(description, fullDesc, dateToCompare);
    }

    /**
     * Formats the task to be saved in hard disk
     * @return a string of the formatted task
     */
    @Override
    public String formatForFile() {
        String done;
        if (isDone) {
            done = "1";
        } else {
            done = "0";
        }

        return type + " | " + done + " | " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
