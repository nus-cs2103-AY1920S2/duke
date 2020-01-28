package command;

/**
 * Tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Events extends Task {
    protected String datetime = "";
    public Events(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     *
     * @param isDone Whether the task is completed by the user
     * @param description The activity description
     * @param datetime The date and time described in /at command
     */
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

    /**
     *
     * @return String to be stored in task text file
     */
    @Override
    public String fileString(){
        return "E|" + getStatusIcon() + "|" + this.description + "|" + this.datetime;
    }
}
