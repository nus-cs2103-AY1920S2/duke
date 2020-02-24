public class Todo extends Task {

    Todo(int done, String taskName) {
        super("todo", done, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
