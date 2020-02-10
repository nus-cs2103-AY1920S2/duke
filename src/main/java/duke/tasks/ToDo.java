package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park.
 */
public class ToDo extends Task {

    /**
     * creates a new ToDo.
     *
     * @param description the description of the todo
     */
    public ToDo(String description) {
        super(description);
        assert description != null : "No description for this todo";
    }

    /**
     * returns the output string.
     *
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        assert this.getDescription() != null : "No description for this todo";
        return "[T]" + super.toString();
    }

    /**
     * returns the file data string.
     *
     * @return String for the file format
     */
    @Override
    public String fileString() {
        assert this.getDescription() != null : "No description for this todo";
        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    @Override
    public void update(String time, DateTimeFormatter format) {
        //this method is unused because a todo doesnt have a timing
    }
}
