package main.duke.tasks;

import main.duke.Parser;
import main.duke.enums.TType;
import main.duke.exception.BadDateException;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public TType getType() {
        return TType.EVENT;
    }

    public Event(int id, String task, String date) throws BadDateException {
        super(id, task);
        this.date = Parser.dateParser(date);
    }

    public Event(int id, boolean done, String task, String date) throws BadDateException {
        super(id, done, task);
        this.date = Parser.dateParser(date);
    }

    @Override
    public String toString() {
        if (done) return  "[E][✓] " + task + " (at: " +
                date.format(Parser.DATE_FORMATTER) + ")";
        else return "[E][✗] " + task + " (at: " +
                date.format(Parser.DATE_FORMATTER) + ")";
    }
}
