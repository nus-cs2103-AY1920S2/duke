import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task{
    private LocalDate date;

    public Deadlines(String newDescripton, String newTime){
        super(newDescripton);
        this.date = LocalDate.parse(newTime);
    }

    @Override
    public String toString(){
        return " [D][" + super.getStatusIcon() + "]" + super.description
                + Integer.valueOf(this.date.getDayOfMonth()).toString() + " "
                    + this.date.getMonth().toString() + " "
                        + Integer.valueOf(this.date.getYear()).toString() ;
    }
}
