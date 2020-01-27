import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class Deadline extends Task implements java.io.Serializable{

    protected LocalDate by;

    public Deadline (String description, LocalDate by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " " + by.getDayOfWeek().toString()  + ")";
    }
}
