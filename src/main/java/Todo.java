public class Todo extends Task{
    public Todo(String command, int index) {
        super(command, index);
    }

    @Override
    public String toString() {
        return "[T][" + getDoneSymbol() + "] " + getCommand();
    }
}
