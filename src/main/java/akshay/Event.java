package akshay;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveFormat() {
        return "E" + " | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.at;
    }
}
