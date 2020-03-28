/**
 * Todo Class.
 * Todo Class is a subclass of Task Class. It only keeps the details of the Todo Task.
 * Todo task is the only child task that does not store date and time unlike
 * its sibling classes Event and Deadline.
 *
 * @author Amos Cheong
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getDesc() {
        return super.description;
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}