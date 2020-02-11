/**
 * Represents a task with an event.
 */
public class ToDoTask extends Task {
    public ToDoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override 
    public int compareTo(Task other) {
        return this.desc.compareTo(other.desc);
    }
}