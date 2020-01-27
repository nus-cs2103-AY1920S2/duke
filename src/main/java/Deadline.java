import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    // To create deadline
    public Deadline(String taskName, String dateTime) {
        super(taskName.trim(), dateTime.trim());
        this.taskType = "D";
    }

    // To initialise save data
    public Deadline(String taskName, boolean isDone, String dateTime) {
        super(taskName.trim(), isDone, dateTime.trim());
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd MM yyyy")) + ")";
    }

}