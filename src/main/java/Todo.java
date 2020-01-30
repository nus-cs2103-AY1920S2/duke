public class Todo extends Task {
    public Todo(String command) {
        super(command);
    }

    public int getDoneInt() {
        return getDone() ? 1 : 0;
    }

    @Override
    public String updateFile() {
        return "T - " + getDoneInt() + " - " + getCommand();
    }

    @Override
    public String toString() {
        return "[T][" + getDoneSymbol() + "] " + getCommand();
    }
}
