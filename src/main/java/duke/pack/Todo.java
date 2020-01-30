package duke.pack;

public class Todo extends Task {
    protected String fullDesc;

    /**
     * creates a to-do type of task
     * @param description task to be done
     * @param fullDesc the full description of the task
     */
    public Todo(String description, String fullDesc) {
        super(description, fullDesc);
    }

    /**
     * Formats the task to be saved in hard disk
     * @return a string of the formatted task
     */
    @Override
    public String formatForFile() {
        String type = "T";
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
