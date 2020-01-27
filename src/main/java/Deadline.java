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

    private void convertTiming(){
        try{
            recordedDate = LocalDate.parse(timing.split(" ")[1]);
        }catch(Exception e){
            System.out.println("Wrong date input");
        }
    }

    public String getTime(){
        return this.recordedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString(){
        return  ". " + "[D] " + complete + task + "(" + timing.split(" ")[1] + " " + getTime() + ")";
    }
}