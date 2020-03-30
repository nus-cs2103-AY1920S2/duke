package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toSaveForm() {
        return "T , " + super.getStatusIcon() + " , " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
