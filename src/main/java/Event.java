package main.java;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {

    protected LocalDate date;
    protected DateTimeFormatter dateConverter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d YYYY");


    public Event(String description, String date) throws ParseException {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getAt() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";
    }
}