public class Event extends Task {
    protected String time;
    Event(String todo, String time) {
        super(todo);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

    @Override
    public java.lang.String toSaveString() {
        return String.format("%s || event || %s || %s", super.toSaveString(), this.task, this.time);
    }
}
