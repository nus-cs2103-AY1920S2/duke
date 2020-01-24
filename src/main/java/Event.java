package main.java;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {

    protected Date date;
    protected SimpleDateFormat dateConverter = new SimpleDateFormat("yyyy-mm-dd");
    protected SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M/yyyy");


    public Event(String description, String date) throws ParseException {
        super(description);
        this.date = dateConverter.parse(date);
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.toString() + " (at: " + dateFormatter.format(date) + ")";
    }
}