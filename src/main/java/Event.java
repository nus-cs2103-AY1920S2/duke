public class Event extends Task{
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString(){
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.date + ")";
    }

}
