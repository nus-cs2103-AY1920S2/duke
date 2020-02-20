package duke.commons;
public class Todo extends Task {

    protected String type;

    public Todo(String type, boolean isDone, String description) {
        super(type, isDone, description);
    }

    public String getTypeSymbol() {
        return "T";
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {getTypeSymbol(), isDoneString, super.description};
    }

    @Override
    public String toString() {
        return "[" + getTypeSymbol() + "]" + super.toString();
    }
}