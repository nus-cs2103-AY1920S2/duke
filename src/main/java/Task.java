public class Task {
    enum TaskType {
        toDo, deadline, event, unknown;
    }

    public static final String doneSymbol = "\u2714";
    public static final String notDoneYetSymbol = "\u2718";
    public static final String toDoCommand = "todo";
    public static final String deadlineCommand = "deadline";
    public static final String eventCommand = "event";

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone)
            return '[' + doneSymbol + "] " + name;
        else
            return '[' + notDoneYetSymbol + "] " + name;
    }
}
