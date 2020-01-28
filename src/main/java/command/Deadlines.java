package command;

public class Deadlines extends Task {
    protected String datetime = "";
    public Deadlines(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Deadlines(boolean isDone, String description, String datetime) {
        super(description);
        this.isDone = isDone;
        this.datetime = datetime;
    }

    @Override
    public String toString(){
        return "[D]" + "[" + getStatusIcon() + "] " + this.description + " (by: " +
                this.datetime + ")";
    }

    @Override
    public String fileString(){
        return "D|" + getStatusIcon() + "|" + this.description + "|" + this.datetime;
    }

    @Override
    public boolean containsString(String keyword){
        return this.toString().contains(keyword) || datetime.contains(keyword);
    }
}
