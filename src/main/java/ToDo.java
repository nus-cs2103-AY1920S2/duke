public class ToDo extends Task {

    /**
     * Constructor for the todo class.
     *
     * @param description description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public void snooze(String by) throws DukeException {
        throw new DukeException("A todo does not have any timing, and therefore cannot be snoozed!");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
