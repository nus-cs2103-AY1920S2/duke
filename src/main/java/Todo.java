/**
 * Represents a todo with a description String and an isDone boolean
 */
public class Todo extends Task{
    /**
     * Creates a Todo object, a basic extension of the Task object
     * @param description a description of the Todo
     */
    public Todo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
