public class Event extends Task {
    private final String bulletin = "[E]";

    private String atTime;
    private TaskType type;

    public Event(String name, String atTime) {
        super(name);
        this.atTime = atTime;
        type = TaskType.event;
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name;
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name;
    }
}
