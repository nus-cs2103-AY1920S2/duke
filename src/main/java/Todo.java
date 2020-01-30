public class Todo extends Task {
    public Todo (String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public String toFormatString() {
        return super.getStatus() ? String.format("T | 1 | %s", super.getTask())
                : String.format("T | 0 | %s", super.getTask());
    }
}