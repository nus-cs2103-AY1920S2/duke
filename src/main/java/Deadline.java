import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
<<<<<<< HEAD
    protected String day;
    public Deadline(String description, String day){
=======
    LocalDate date;
    public Deadline(String description, LocalDate date){
>>>>>>> branch-Level-8
        super (description);
        this.date = date;
    }

    public String toStringTaskstxt(){
        return "D/" + getStatusIconInBin() + "/" + description + "/" + day + "\n";
    }

    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[D]" + super.toString() + "(by: " + fullDate + ")";
    }
}
