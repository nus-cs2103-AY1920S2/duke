public class Todo extends Task {

<<<<<<< HEAD
    protected String type;

    public Todo(boolean isDone, String description) {
        super(isDone, description);
        this.type = "T";
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {this.type, isDoneString, super.description};
=======
    public Todo(boolean isDone, String description) {
        super(isDone, description);
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}