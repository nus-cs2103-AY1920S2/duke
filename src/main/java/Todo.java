public class Todo extends Task {

    protected String type;

    public Todo(boolean isDone, String description) {
        super(isDone, description);
        this.type = "T";
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {this.type, isDoneString, super.description};
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}