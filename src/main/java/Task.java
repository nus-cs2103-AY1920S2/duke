import java.util.Date;

public abstract class Task {
    protected String description; //action to be taken
    protected boolean isDone; //status of the action
    private Ui ui = new Ui();
    protected String statusIcon;
    protected Date[] dateTimes = new Date[2];


    public String output() {
        return "";
    }

    /**
     * This methods updates the Task action and indicate that it is incomplete.
     * @param description This is the action for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        //this.statusIcon = "\u2718";
        this.statusIcon = "✗";
    }

    /**
     * This methods changes the status icon.
     */
    public void getStatusIcon() {
        if (isDone) {
            //this.statusIcon = "\u2713";
            this.statusIcon = "✓";
        } else {
            //this.statusIcon = "\u2718";
            this.statusIcon = "✗";
        }
    }

    /**
     * This method set the status icon to 1 for easier storage.
     */
    public void setStatusIcon() {
        this.statusIcon =  "1";
    }

    public Date getTime() {
        return dateTimes[1];
    }

    public String getStrTime() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + statusIcon + "] " + description;
    }



}