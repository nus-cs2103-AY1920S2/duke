package tasks;

import java.time.LocalDate;

public abstract class DateTask extends Task{
    public DateTask(String description) {
        super(description);
    }

    public abstract LocalDate getDate();
}
