package duke;

public class Deadline extends Task {

    DateAndTime dateAndTime;
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        dateAndTime = new DateAndTime(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateAndTime.formatDateToString() + ")";
    }
}