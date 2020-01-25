public class Events extends Task{
    protected String datetime = "";
    public Events(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString(){
        return "[E]" + "[" + getStatusIcon() + "] " + this.description + " (at: " +
                this.datetime + ")";
    }
}
