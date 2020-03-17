package sampletest;

import java.time.LocalDate;
import exception.ExceptionGenerator;


/**
 * CS2103 Individual Project
 * Event is a subclass of task which takes an additional variable time.
 * @author Wei Cheng
 */

public class Events extends Task {
    private LocalDate date;

    /**
     * Constructor for the Events by taking in a description and time.
     * @param newDescripton Description of events.
     * @param newDate time of event.
     */

    public Events(String newDescripton, String newDate) {
        super(newDescripton);
        ExceptionGenerator.checkDateFormat(newDate);
        this.date = LocalDate.parse(newDate);
    }

    /**
     * Constructor that is used when we are loading the data from the saved file.
     * @param newDescripton Description of events.
     * @param newTime time of event.
     * @param status status of event.
     */

    public Events(String newDescripton, String newTime, String status) {
        super(newDescripton, status);
        this.date = LocalDate.parse(newTime);
    }

    /**
     * obtain the date.
     * @return string representation of the date.
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
        return date.getDayOfMonth() * 1000000 + date.getMonthValue() * 10000 + date.getYear();
    }

    /**
     * LocalDate representation.
     * @return localDate representation of the file.
     */

    public LocalDate getDate() {
        return date;
    }

    /**
     * The toString method.
     * @return String representation of event.
     */

    @Override
    public String toString() {
        return " [E][" + super.getStatusIcon() + "]" + super.description + " "
               + Integer.valueOf(this.date.getDayOfMonth()).toString() + " "
                 + this.date.getMonth().toString() + " "
                    + Integer.valueOf(this.date.getYear()).toString();
    }
}
