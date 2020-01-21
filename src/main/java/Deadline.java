public class Deadline extends Task {
    private final String bulletin = "[D]";

    private String byTime;
    private TaskType type;

    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
        type = TaskType.deadline;
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name;
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name;
    }
}
