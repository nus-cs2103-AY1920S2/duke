import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    protected String type = "D";
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        time = time.trim();
        this.time = time;

        try {
            format_Date();
        } catch (Exception e) {
            System.out.println("PS! Timing is not recorded... Follow the format dd/MM/YYYY HHMM!");
        }
    }


    public void format_Date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm").withLocale(Locale.ENGLISH);
        LocalDate date = LocalDate.parse(time,formatter);
    }


    @Override
    public String toString() {
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (by: " +  time + ")";
        return temp;
    }
}