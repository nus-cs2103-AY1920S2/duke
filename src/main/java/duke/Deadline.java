package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This Deadline class extends to Task class
 * @param by - to keep track of the time/date of a particular event
 */

public class Deadline extends Task {

    protected LocalDate by;
    protected String details;
    public Deadline(String description, String input){
        super(description);
        this.details =  input;
        this.by = LocalDate.parse(input);
    }

    public String toString(){
        return "[D]" + super.toString() + "(by: " + extraInfo() + ")";
    }

    /**
     * This method is to the paremeter "when." It will override the parent's class of the same method.
     * @return a formated string, concat withe paremeter "when"
     */
    public LocalDate getWhen(){
        return by;
    }

    /**
     * This method is to return the paremeter "by." It will override the parent's class of the same method.
     * @return by
     */
    public String extraInfo(){
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getStringDate(){
        return details;
    }
}
