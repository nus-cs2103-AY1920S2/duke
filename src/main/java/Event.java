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
}
