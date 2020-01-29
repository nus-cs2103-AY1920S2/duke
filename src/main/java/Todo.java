/**
 * This is a subclass of task which simulates todo tasks.
 */
public class Todo extends Task{

    /**
     * Class constructor inherits Task constructor.
     *
     * @param description Description of this todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Override String representation of todo tasks.
     *
     * @return Type T and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T" + super.getData();
    }
}
