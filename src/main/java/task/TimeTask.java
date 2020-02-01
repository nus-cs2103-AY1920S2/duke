package task;

import exception.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import parser.Parser;

public class TimeTask extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public TimeTask(String type, String description) throws DukeException {
        super(type, Parser.getContent(description));
    }

    public TimeTask(String type, String[] fromMemory) {
        super(type, fromMemory[1], fromMemory[2]);
        this.date = LocalDate.parse(fromMemory[3]);
        this.time = LocalTime.parse(fromMemory[4]);
    }

    /** @return String returns string used to store task in txt file */
    @Override
    public String toStorable() {
        return String.format("%s|%s|%s", super.toStorable(), this.date, this.time);
    }
}
