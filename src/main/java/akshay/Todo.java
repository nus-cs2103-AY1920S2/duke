package akshay;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean mark) {
        super(description, mark);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveFormat() {
        return "T" + " , " + (super.isDone ? "1" : "0") + " , " + super.description;
    }
}
