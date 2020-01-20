public class Event extends Task {
    public final String dateTime;

    public Event(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[E]" + doneCheck + this.name;
        } else {
            return "[E]" + notDoneCheck + this.name;
        }
    }
}