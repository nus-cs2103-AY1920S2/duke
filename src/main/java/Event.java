public class Event extends Task {

    DateAndTime dateAndTime;
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
        dateAndTime = new DateAndTime(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime.formatDateToString() + ")";
    }
}