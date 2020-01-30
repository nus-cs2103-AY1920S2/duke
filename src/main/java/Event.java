public class Event extends Task {

    private String date;

    public Event (String description, String date){
        super(description);
        this.date = date;
    }

    public Event (String description, boolean done, String date){
        super(description);
        this.isDone = done;
        this.date = date;
    }

    public String toString(){
        return "[E][" + getStatusIcon() + "] " + getDescription() + "(at:" + this.date + ")";
    }

    public String toParser(){
        return "E /" + getStatusIcon() + "/" + this.description + "/" + this.date;
    }
}