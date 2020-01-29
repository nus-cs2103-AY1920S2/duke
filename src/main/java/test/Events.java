package test;

import java.time.LocalDate;

/**
 * CS2103 Individual Project
 * @author Wei Cheng
 * Event is a subclass of task which takes an additional variable time
 */

public class Events extends test.Task{
    private LocalDate date;

    /**
     * Constructor for the Events by taking in
     * a description and time
     * @param newDescripton Description of events
     * @param newTime time of event
     */
    public Events(String newDescripton, String newTime){
        super(newDescripton);
        this.date = LocalDate.parse(newTime);
    }

    /**
     * Constructor that is used when we are loading the data from the saved file
     * @param newDescripton Description of events
     * @param newTime time of event
     * @param status status of event
     */
    public Events(String newDescripton, String newTime, String status){
        super(newDescripton, status);
        this.date = LocalDate.parse(newTime);
    }

    /**
     * @return string representation of the date
     */
    public String getTime(){
        return Integer.valueOf(this.date.getDayOfMonth()).toString() + " "
                + this.date.getMonth().toString() + " "
                + Integer.valueOf(this.date.getYear()).toString() ;
    }

    /**
     * LocalDate representation
     * @return localDate representation of the file
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return String representation of event.
     */
    @Override
    public String toString(){
        return " [E][" + super.getStatusIcon() + "]" + super.description + " "
               + Integer.valueOf(this.date.getDayOfMonth()).toString() + " "
                 + this.date.getMonth().toString() + " "
                    + Integer.valueOf(this.date.getYear()).toString() ;
    }
}
