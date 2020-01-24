package main.java;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    protected Date date;
    protected SimpleDateFormat dateConverter = new SimpleDateFormat("yyyy-mm-dd");
    protected SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M/yyyy");

    public Deadline(String description, String date) throws ParseException {
        super(description);
        this.date = dateConverter.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + " " + super.toString() + " (by: " + dateFormatter.format(date) + ")";
    }
}