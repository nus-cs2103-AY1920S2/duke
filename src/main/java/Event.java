import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends AbstractTask {
    LocalDate date;
    String time;
    String[] parseDateTime;

    public Event(String taskName, String dateTime){
        super(taskName);
        parseDateTime = dateTime.split(" ",  3);
        this.date = LocalDate.parse(parseDateTime[1]);
        if (parseDateTime.length > 2){
            this.time = parseDateTime[2];
        }
    }

    private String formattedDate(){
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString(){
        return (this.time != null) ? "[E]" + taskStateString() + " " + this.taskName + parseDateTime[0] + " " + formattedDate() + " " + this.time
                : "[E]" + taskStateString() + " " + this.taskName + parseDateTime[0] + " " + formattedDate();
    }

}
