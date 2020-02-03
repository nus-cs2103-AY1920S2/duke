public class Event extends Task {
    public Event(String description, String time) {
        super(description, time);
    }

    @Override
    public String getTypeName() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String toStringFile() {
        return "E" + " | " + super.toStringFile();
    }
}
