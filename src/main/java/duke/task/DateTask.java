package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateTask extends Task {

    protected LocalDate date;
    protected String connectorWord;

    public DateTask(String msg, LocalDate date, String connector) {
        super(msg);
        this.date = date;
        this.connectorWord = connector;
    }

    public void changeDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public String writeToFile() {
        return super.writeToFile() + " , " + date;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + connectorWord + ": "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
