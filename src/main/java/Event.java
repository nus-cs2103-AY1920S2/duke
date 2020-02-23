public class Event extends Task {
    private String time;

    public Event(String instruction, String time) {
        super(instruction);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
