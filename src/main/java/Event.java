import java.util.Date;

public class Event extends Task {

    public String DateTime;

    public Event(String taskname, String DateTime) {
        super(taskname, "E");
        this.DateTime = DateTime;
    }

    @Override
    public String toString() {
        String message = "";
        if (this.done) {
            message += "[" + this.Tasktype +"]" + "[" + "\u2713" + "] " + this.taskname + " (at: " + this.DateTime + ")";
        } else {
            message += "[" + this.Tasktype +"]" + "[" + "\u2718" + "] " + this.taskname + " (at: " + this.DateTime + ")";
        }
        return message;
    }
}
