package sampletest;

import java.time.LocalDate;

/**
 * CS2103 Individual Project.
 * Deadlines is a subclass of task which takes an additional variable time.
 * @author Wei Cheng
 */

public class Deadlines extends Task {
    private LocalDate date;
    /**
     * Constructor for the Deadlines by taking in a description and time.
     * @param newDescripton description of deadline.
     * @param newTime time of the deadline.
     */

    public Deadlines(String newDescripton, String newTime) {
        super(newDescripton);
        this.date = LocalDate.parse(newTime);
    }

    /**
     * Constructor that is used when we are loading the data from the saved file.
     * @param newDescripton description of deadline.
     * @param newTime time of the deadline.
     * @param status status of the deadline.
     */

    public Deadlines(String newDescripton, String newTime, String status) {
        super(newDescripton, status);
        this.date = LocalDate.parse(newTime);
    }

    /**
     * return a String representation of the date.
     * @return String.
     */

    public String getTime() {
        return Integer.valueOf(this.date.getDayOfMonth()).toString() + " "
                + this.date.getMonth().toString() + " "
                + Integer.valueOf(this.date.getYear()).toString();
    }

    public boolean isUrgent() {
        return true;
    }

    public int getCompletionDate() {
        return date.getDayOfMonth() * 1000000 + date.getMonthValue()  * 10000 + date.getYear();
    }

    /**
     * return the date in the LocalDate representation.
     * @return LocalDate.
     */

    public LocalDate getDate() {

        return date;
    }
    /**
     * Return a String representation of the Event objects.
     * @return String.
     */

    @Override
    public String toString() {
        return " [D][" + super.getStatusIcon() + "]" + super.description + " "
                + Integer.valueOf(this.date.getDayOfMonth()).toString()
                + "-"  + this.date.getMonth().toString() + "-"
                        + Integer.valueOf(this.date.getYear()).toString();
    }
}
