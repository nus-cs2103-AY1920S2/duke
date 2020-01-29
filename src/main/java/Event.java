public class Event extends Task {

    protected String at;

    public Event (String description, String at) {
        super (description);
        this.at = at;
    }

    @Override
    public String saveFile() {
        if (this.status.equals("Done")) {
            return  "E|1||" + this.description + "|||" + this.at;
        } else {
            return  "E|0||" + this.description + "|||" + this.at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}

