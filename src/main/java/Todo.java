public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.type = Type.T;
    }

    public String toString() {
        return "[" + super.getType() + "]" + super.toString();
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription();
    }
}
