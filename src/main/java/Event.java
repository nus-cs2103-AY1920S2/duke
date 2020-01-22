public class Event extends Task {

    protected String type = "E";
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (at:" +  time + ")";
        return temp;
    }


}