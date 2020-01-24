package duke.tasks;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toSaveable() {
        return String.format("todo\n%s\n%s", name, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatus(), name);
    }
}