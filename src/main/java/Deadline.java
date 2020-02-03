import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends AbstractTask {
    LocalDate date;
    String time;
    String[] parseDateTime;
    String preposition;

    public Deadline(String taskName, String preposition, String dateTime){
        super(taskName);
        parseDateTime = dateTime.split(" ",  2);
        this.date = LocalDate.parse(parseDateTime[0]);
        this.preposition = preposition;
        if (parseDateTime.length > 1){
            this.time = parseDateTime[1];
        }
    }

    private String formattedDate(){
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString(){
        return (this.time != null) ? "[D]" + taskStateString() + " " + this.taskName + " " + this.preposition + " " + formattedDate() + " " + this.time
                : "[D]" + taskStateString() + " " + this.taskName + " " + this.preposition + " " + formattedDate();
    }

}
