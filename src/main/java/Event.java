import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
<<<<<<< HEAD
    protected String dayAndDuration;
    public Event(String description, String dayAndDuration){
=======
    LocalDate date;
    public Event(String description, LocalDate date){
>>>>>>> branch-Level-8
        super (description);
        this.date = date;
    }

    public String toStringTaskstxt(){
        return "D/" + getStatusIconInBin() + "/" + description + "/" + dayAndDuration + "\n";
    }

    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[E]" + super.toString() + "(at: " + fullDate + ")";
    }
}
