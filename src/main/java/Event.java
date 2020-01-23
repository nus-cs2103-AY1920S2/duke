public class Event extends Item {

    private String keyword;
    private String time;

    /**
     * Contstructor of the event object.
     * @param name The event name.
     * @param keyword The keyword of the event.
     * @param time The event date/time.
     */
    public Event(String name, String keyword, String time) {
        super(name);
        this.keyword = keyword;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s: %s)", super.toString(), keyword, time);
    }
}