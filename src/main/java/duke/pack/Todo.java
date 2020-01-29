package duke.pack;

public class Todo extends Task {
    /**
     * creates a to-do type of task
     * @param description task to be done
     */
    public Todo(String description) {
        super(description);
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
