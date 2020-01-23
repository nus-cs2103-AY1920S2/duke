public class Deadline extends Task {

    private String date;

    public Deadline (String description, String date){
        super(description);
        this.date = date;
    }

    public String toString(){
        return "[D][" + getStatusIcon() + "] " + getDescription() + "(by:" + this.date + ")";
    }
}