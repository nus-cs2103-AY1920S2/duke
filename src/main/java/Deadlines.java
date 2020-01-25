public class Deadlines extends Task{
    protected String datetime = "";
    public Deadlines(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString(){
        return "[D]" + "[" + getStatusIcon() + "] " + this.description + " (by: " +
                this.datetime + ")";
    }
}
