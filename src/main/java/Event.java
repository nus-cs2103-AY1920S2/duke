public class Event extends Task {

    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTaskType() {
        return "E";
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String myword = "";
        myword = myword + "[" + this.getTaskType() + "]"
                + " [" + super.getStatusIcon() + "] " + super.description +
                " (" + this.time + ")";

        return myword;
    }
}
