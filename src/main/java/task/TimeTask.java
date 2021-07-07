package task;

import exception.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import parser.Parser;

public class TimeTask extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public TimeTask(String type, String description, String dateTime) throws DukeException {
        super(type, Parser.getContent(description));
        this.time = Parser.getTime(dateTime);
        this.date = Parser.getDate(dateTime);
    }

    public TimeTask(String type, String[] fromMemory) {
        super(type, fromMemory[Constant.isDoneIndex], fromMemory[Constant.descriptionIndex]);
        this.date = LocalDate.parse(fromMemory[Constant.dateIndex]);
        this.time = LocalTime.parse(fromMemory[Constant.timeIndex]);
    }

    /** @return String returns string used to store task in txt file */
    @Override
    public String toStorable() {
        return String.format("%s|%s|%s", super.toStorable(), this.date, this.time);
    }
}
