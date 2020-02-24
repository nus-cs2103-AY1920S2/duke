public class Todo extends Task {
    /**
     * This method updates the action to be taken.
     * @param description This is the action to be taken.
     */
    public Todo(String description) {
        super(description);
        assert description != null : "Description cannot be empty";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This method indicates that a new Task has been added to the ArrayList in Store.
     */
    public String output() {
        return "Got it. I've added this task: \n" + " [T]" + super.toString();
    }
}