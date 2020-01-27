import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event (LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    @Override
    public String toString() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = super.getDateTime().format(format);

        return "[" + Types.Event + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(at:" + date + ")";
    }

    @Override
    public Types getType() {

        return Types.Event;

    }
}
