public class Event extends Task {
    protected String time;

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String str = "[E]";
        if (this.isDone) {
            str += "[\u2713] ";
        } else {
            str += "[\u2718] ";
        }
        str += this.description;
        str += " (at: " + this.time + ")";
        return str;
    }
}