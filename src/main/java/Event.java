public class Event extends Item {

    private String time;

    /**
     * Contstructor of the event object.
     * @param name The event name.
     * @param time The event date/time.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}