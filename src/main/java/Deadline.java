public class Deadline extends Task {

    protected String type = "D";
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (by:" +  time + ")";
        return temp;
    }
}