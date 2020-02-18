package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class DateTask extends Task {

    protected LocalDate date;
    protected String connectorWord;

    /**
     * Creates a DateTask with the details, date and connector word (at/by) stored.
     * Status of the Task is instantiated to X.
     * @param msg Details of the Task.
     * @param date Date of the DateTask.
     * @param connector String for the connector word (at/by).
     */
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
