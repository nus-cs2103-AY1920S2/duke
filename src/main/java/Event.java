public class Event extends Task {
    private final String bulletin = "[E]";

    private String atTime;
    private TaskType type;

    public Event(String name, String atTime) {
        super(name);
        this.atTime = atTime;
        type = TaskType.event;
    }

    public Event(String name, boolean isDone, String byTime) {
        super(name, isDone);
        this.atTime = byTime;
        type = TaskType.deadline;
    }

    public String encoder() {
        return String.format("E:%s:%d:%s\n", super.name, (super.isDone ? 1 : 0), atTime);
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name + " " + atTime;
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name + " " + atTime;
    }
}
