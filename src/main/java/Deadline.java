import java.util.Date;

public class Deadline extends Task {

    public String DateTime;

    public Deadline(String taskname, String DateTime) {
        super(taskname, "D");
        this.DateTime = DateTime;
    }

    @Override
    public String toString() {
        String message = "";
        if (this.done) {
            message += "[" + this.Tasktype +"]" + "[" + "\u2713" + "] " + this.taskname + " (by: " + this.DateTime + ")";
        } else {
            message +=  "[" + this.Tasktype +"]" + "[" + "\u2718" + "] " + this.taskname + " (by: " + this.DateTime + ")";
        }
        return message;
    }
}
