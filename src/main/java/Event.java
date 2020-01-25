import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    LocalDate date;
    public Event(String description, LocalDate date){
        super (description);
        this.date = date;
    }

    public String toStringTaskstxt(){
        return "E/" + getStatusIconInBin() + "/" + description + "/" + date.toString() + "\n";
    }

    @Override
    public String toString(){
        String fullDate = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " +
                Integer.toString(date.getDayOfMonth()) + " " + Integer.toString(date.getYear());
        return "[E]" + super.toString() + "(at: " + fullDate + ")";
    }
}
