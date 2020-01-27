public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getDesc() {
        return super.description;
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}