import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    @Override
    public String toString() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = super.getDateTime();
        String date = super.getDateTime().format(format);

        return "[" + Types.Deadline + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(by:" + date + ")";
    }

    @Override
    public Types getType() {

        return Types.Deadline;

    }
}
