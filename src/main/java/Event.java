public class Event extends Task{
    protected String dates;

    public Event(String command, String dates) {
        super(command);
        this.dates = dates;
    }

    @Override
    public String toStringTxt() {
        return "E/" + super.getIcon() + "/" + command + "/" + dates + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dates + ")";
    }
}
