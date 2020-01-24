import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
<<<<<<< HEAD
    LocalDate date;
    public Deadline(String description, LocalDate date){
        super (description);
        this.date = date;
=======
    String day;
    public Deadline(String description, String day){
        super (description);
        this.day = day;
>>>>>>> parent of 254e72b... Level 7 Done
    }

    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                            Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[D]" + super.toString() + "(by: " + fullDate + ")";
    }
}
