public class EventTask extends Task {
    String at;
    public EventTask(String desc, String at) {
        super(desc);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}