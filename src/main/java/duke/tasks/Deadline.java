package duke.tasks;

import duke.Parser;
import duke.enums.TType;
import duke.exception.BadDateException;

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

    public Deadline(boolean isDone, String task, String date) throws BadDateException {
        super(isDone, task);
        this.date = Parser.dateParser(date);
    }

    @Override
    public String toString() {
        if (isDone) return  "[D][✓] " + task + " (by: " +
                date.format(Parser.DATE_READ_FORMATTER) + ")";
        else return "[D][✗] " + task + " (by: " +
                date.format(Parser.DATE_READ_FORMATTER) + ")";
    }
}
