public class Event extends Task{
    String at;
    public Event(String[] description) {
        super(description[0]);
        at = (description[1].split(" ", 2))[1];
    }

    public Event(String description, String atDate, boolean isDone) {
        super(description, isDone);
        at = atDate;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String addToFile() {
        return "E | " + super.addToFile() + " | " + at;
    }
}
