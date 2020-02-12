/**
 * This Event class extends to Task class
 * @param when - to keep track of the time/date of a particular event
 */
public class Event extends Task {
    protected String when;

    public Event(String description, String when){
        super(description);
        this.when = when;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + when + ")";
    }

    /**
     * This method is to the paremeter "when." It will override the parent's class of the same method.
     * @return a formated string, concat withe paremeter "when"
     */
    @Override
    public String getWhen(){
        return "(at: " + this.when + ")";
    }
    /**
     * This method is to return the paremeter "when." It will override the parent's class of the same method.
     * @return when
     */
    public String extraInfo(){
        return this.when;
    }


}
