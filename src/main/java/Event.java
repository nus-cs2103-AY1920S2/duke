import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Event extends Task {

    protected String type = "E";
    protected String time;

    public Event(String description, String time) {
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
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (at: " +  time + ")";
        return temp;
    }


}