import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
<<<<<<< HEAD
    LocalDate date;
    public Event(String description, LocalDate date){
        super (description);
        this.date = date;
=======
    String dayAndDuration;
    public Event(String description, String dayAndDuration){
        super (description);
        this.dayAndDuration = dayAndDuration;
>>>>>>> parent of 254e72b... Level 7 Done
    }

    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[E]" + super.toString() + "(at: " + fullDate + ")";
    }
}
