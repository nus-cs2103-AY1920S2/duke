package main.java;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected DateTimeFormatter dateConverter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d YYYY");

    public Deadline(String description, String date) throws ParseException {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getBy() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + " " + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")";
    }
}