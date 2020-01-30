/**
 * ToDos: tasks without any date/time attached to it e.g., visit new theme park
 */
public class ToDo extends Task {

    /**
     * @param description the description of the todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @return String to be output to the user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return String for the file format
     */
    @Override
    public String fileString() {
        return "T | " + this.getStatusIcon() + " | " + description;
    }
}