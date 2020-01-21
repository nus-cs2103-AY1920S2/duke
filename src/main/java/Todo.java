public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public String getTask() {
        return description;
    }
}
