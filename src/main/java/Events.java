public class Events extends Task{
    protected String datetime = "";
    public Events(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Events(boolean isDone, String description, String datetime) {
        super(description);
        this.isDone = isDone;
        this.datetime = datetime;
    }

    @Override
    public String toString(){
        return "[E]" + "[" + getStatusIcon() + "] " + this.description + " (at: " +
                this.datetime + ")";
    }

    @Override
    public String fileString(){
        return "E|" + getStatusIcon() + "|" + this.description + "|" + this.datetime;
    }
}
