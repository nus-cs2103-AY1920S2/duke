import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Deadlines extends Task{
    protected String by;

    public Deadlines(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        System.out.println(this);
    }

    public String getDeadline() {
        return this.by.toString();
    }

    @Override
    public String toString() {
        LocalDate date = LocalDate.parse(by);
        Format formatter = new SimpleDateFormat("MMM");
        String simpleMonth = date.getMonth().toString().substring(0,3);
        String formattedDate = simpleMonth + " " + date.getDayOfMonth() + " " + date.getYear();
        return "[D]" + super.toString() + "(by:" + formattedDate + ")";
    }
}
