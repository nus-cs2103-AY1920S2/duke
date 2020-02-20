package duke.task;

import java.time.LocalDate;
import duke.main.Constant;

public class Deadline extends Task {
    public final LocalDate date;

    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date, Constant.FORMATTER_INPUT_DATE);
    }

    public Deadline(String name, boolean completed, String date) {
        super(name, completed);
        this.date = LocalDate.parse(date, Constant.FORMATTER_INPUT_DATE);
    }

    public Deadline(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public boolean compareDate(LocalDate inputDate) {
        return this.date.equals(inputDate);
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.name, true, this.date);
    }

    @Override
    public String storeFormat() {
        return "D| |" + completed + "| |" + name + "| |" + date.format(Constant.FORMATTER_INPUT_DATE);
    }

    @Override
    public String toString() {
        if (completed) {
            return "[D]" + Constant.CHECK_DONE + this.name + " (by: " 
                + date.format(Constant.FORMATTER_OUTPUT_DATE) + ")";
        } else {
            return "[D]" + Constant.CHECK_NOTDONE + this.name + " (by: " 
                + date.format(Constant.FORMATTER_OUTPUT_DATE) + ")";
        }
    }
}