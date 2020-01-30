package duke.tasks;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park.
 */
public class ToDo extends Task {

    /**
     * creates a new ToDo.
     * @param description the description of the todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * returns the output string.
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * returns the file data string.
     * @return String for the file format
     */
    @Override
    public String fileString() {
        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }
}
