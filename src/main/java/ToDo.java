public class ToDo extends Task {
    private final String bulletin = "[T]";

    private TaskType type;

    public ToDo(String name) {
        super(name);
        type = TaskType.toDo;
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name;
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name;
    }
}
