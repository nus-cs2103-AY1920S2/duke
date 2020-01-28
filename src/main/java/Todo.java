/**
 * Todo Class.
 * Todo Class is a subclass of Task Class. It only keeps the details of the Todo Task.
 *
 * @author Amos Cheong
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Get the description of this Todo object.
     * @return String description of this Todo object.
     */
    public String getDesc() {
        return super.description;
    }

    /**
     * Get the type of this object.
     * Used when outputting to user.
     * @return String The object type.
     */
    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}