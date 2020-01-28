public class Event extends Task {
    protected String at;

    public Event(String Description, String a) {
        super(Description);
        this.at = a;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String generateWriteFormat() {
        int k = this.isDone ? 1 : 0;
        return "D|"+ k + "|" + description + "|" + at;
    }
}
