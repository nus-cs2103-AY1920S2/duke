class Event extends Task {
    private final String time;

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    private Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public Event complete() {
        return new Event(description, time, true);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
