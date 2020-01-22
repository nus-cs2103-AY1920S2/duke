public class Deadline extends Task {
    protected String time;

    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String str = "[D]";
        if (this.isDone) {
            str += "[\u2713] ";
        } else {
            str += "[\u2718] ";
        }
        str += this.description;
        str += " (by: " + this.time + ")";
        return str;
    }
}