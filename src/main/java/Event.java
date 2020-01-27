public class Event extends task {
    private String date;

    public Event (String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
