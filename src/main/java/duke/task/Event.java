package duke.task;

import java.time.LocalDateTime;
import java.time.LocalDate;
import duke.main.Constant;

public class Event extends Task {
    public final LocalDateTime dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime, Constant.FORMATTER_INPUT_DATE_TIME);
    }

    public Event(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = LocalDateTime.parse(dateTime, Constant.FORMATTER_INPUT_DATE_TIME);
    }

    public Event(String name, boolean completed, LocalDateTime dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public Event complete() {
        return new Event(this.name, true, this.dateTime);
    }

    @Override
    public String storeFormat() {
        return "E| |" + completed + "| |" + name + "| |" + dateTime.format(Constant.FORMATTER_INPUT_DATE_TIME);
    }

    public boolean compareDate(LocalDate inputDate) {
        return this.dateTime.toLocalDate().equals(inputDate);
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[E]" + doneCheck + this.name + " (at: " + dateTime.format(Constant.FORMATTER_OUTPUT_DATE_TIME)
                    + ")";
        } else {
            return "[E]" + notDoneCheck + this.name + " (at: " + dateTime.format(Constant.FORMATTER_OUTPUT_DATE_TIME)
                    + ")";
        }
    }
}