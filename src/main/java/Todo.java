/**
 * Specific form of a todo task that is input by the user to Duke.
 */
public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toLine() {
        int num;
        if (super.done) {
            num = 1;
        } else {
            num = 0;
        }
        return "T/" + num + "/" + super.task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}