public class ToDo extends Task {

    /**
     * Constructor for the ToDo object, a subclass of Task
     * @param description String containing the description of the task
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Formats this object as a String to be printed out
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}