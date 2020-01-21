public class Todo extends Task {
    public Todo(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "[T][" + getDoneSymbol() + "] " + getCommand();
    }
}
