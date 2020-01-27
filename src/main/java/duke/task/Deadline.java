package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            String[] dateTimeArr = by.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            this.time = LocalTime.parse(dateTimeArr[1]);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm");
        }
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        String[] dateTimeArr = by.split(" ");
        this.date = LocalDate.parse(dateTimeArr[0]);
        this.time = LocalTime.parse(dateTimeArr[1]);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + date + " " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")";
    }
}
