package task;

import exception.DukeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import parser.Parser;

public class PeriodTask extends TimeTask {
    protected LocalDate endDate;
    protected LocalTime endTime;

    public PeriodTask(String description) throws DukeException {
        super(
                Constant.PERIODTASK.getType(),
                description,
                Parser.getStartEndDateTime(
                        description,
                        Constant.PERIODTASK.getTimeDelimiter(),
                        Constant.PERIODTASK.getEndTimeDelimiter())[0]);
        String dateTimes[] =
                Parser.getStartEndDateTime(
                        description,
                        Constant.PERIODTASK.getTimeDelimiter(),
                        Constant.PERIODTASK.getEndTimeDelimiter());
        this.endDate = Parser.getDate(dateTimes[1]);
        this.endTime = Parser.getTime(dateTimes[1]);
        LocalDateTime start = LocalDateTime.of(this.date, this.time);
        LocalDateTime end = LocalDateTime.of(this.endDate, this.endTime);

        if (end.isBefore(start)) {
            throw new DukeException("End dates and time must be after start dates and time");
        }
    }

    public PeriodTask(String[] fromMemory) {
        super(Constant.PERIODTASK.getType(), fromMemory);
        this.endDate = LocalDate.parse(fromMemory[5]);
        this.endTime = LocalTime.parse(fromMemory[6]);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (start: %s %s, end: %s %s)",
                super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                this.time,
                this.endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                this.endTime);
    }

    @Override
    public String toStorable() {
        return String.format("%s|%s|%s", super.toStorable(), this.endDate, this.endTime);
    }
}
