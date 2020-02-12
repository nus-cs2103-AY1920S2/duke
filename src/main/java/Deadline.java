/**
 * This Deadline class extends to Task class
 * @param by - to keep track of the time/date of a particular event
 */

public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * This method is to the paremeter "when." It will override the parent's class of the same method.
     * @return a formated string, concat withe paremeter "when"
     */
    public String getWhen(){
        return "(by: " + this.by + ")";
    }

    /**
     * This method is to return the paremeter "by." It will override the parent's class of the same method.
     * @return by
     */
    public String extraInfo(){
        return this.by;
    }
}
