/**
 * Represents a Todo Task object
 */
public class Todo extends Task {

    /**
     * Creates a Todo object
     *
     * @param description Describes the Todo object to be created
     */
    public Todo (String description) {
        super (description);
    }

    /**
     * Formats the Todo object into a string that can be saved into hard disk
     *
     * @return A string that is formatted to be saved into hard disk
     */
    @Override
    public String saveFile() {
        if (this.status.equals ("Done")) {
            return  "T|1||" + this.description;
        } else {
            return  "T|0||" + this.description;
        }
    }

    /**
     * Converts Todo object into string format to describe the task
     *
     * @return A string that describes the task and what type of task it is
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
