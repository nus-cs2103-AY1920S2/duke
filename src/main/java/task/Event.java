package task;

/**
 * Event subclass which extends from task.Task ( parent class )
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    /**
     * @return the event description and its status icon
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription() + "(" + at + ")" + " tag:" + super.getTag();
    }

    @Override
    public String getDate(){
        return this.at;
    }
}
