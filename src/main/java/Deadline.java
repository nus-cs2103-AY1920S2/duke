import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String timing;
    private LocalDate recordedDate;

    public Deadline(String task, String timing){
        super(task);
        this.timing = timing;
        convertTiming();
        //this.timing = LocalDate.parse(timing);
        //this.timeString = this.timing.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String task, String timing, boolean bool){
        super(task, bool);
        this.timing = timing;
        convertTiming();
    }

    private void convertTiming(){
        this.recordedDate = LocalDate.parse(timing.split(" ")[1]);
    }

    public String getTiming(){
        return timing;
    }

    public String getTime(){
        return this.recordedDate.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    @Override
    public String toString(){
        return  ". " + "[D] " + complete + task + "(" + timing.split( " ")[0].split("/")[0] + " " + getTime() + ")";
    }
}