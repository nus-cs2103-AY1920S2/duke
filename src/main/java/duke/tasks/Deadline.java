package main.java.duke.tasks;

import main.java.duke.Parser;
import main.java.duke.enums.TType;
import main.java.duke.exception.BadDateException;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public TType getType() {
        return TType.DEADLINE;
    }

    public Deadline(String task, String date) throws BadDateException {
        super(task);
        this.date = Parser.dateParser(date);
    }

    public Deadline(boolean done, String task, String date) throws BadDateException {
        super(done, task);
        this.date = Parser.dateParser(date);
    }

    @Override
    public String toString() {
        if (done) return  "[D][✓] " + task + " (by: " +
                date.format(Parser.DATE_FORMATTER) + ")";
        else return "[D][✗] " + task + " (by: " +
                date.format(Parser.DATE_FORMATTER) + ")";
    }
}
