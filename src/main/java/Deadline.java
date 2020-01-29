public class Deadline extends Task {
    private final String bulletin = "[D]";

    private String byTime;
    private TaskType type;

    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = byTime;
        type = TaskType.deadline;
    }

    public Deadline(String name, boolean isDone, String byTime) {
        super(name, isDone);
        this.byTime = byTime;
        type = TaskType.deadline;
    }

    public String encoder() {
        return String.format("D:%s:%d:%s\n", super.name, (super.isDone ? 1 : 0), byTime);
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name + " " + byTime;
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name + " " + byTime;
    }
}
