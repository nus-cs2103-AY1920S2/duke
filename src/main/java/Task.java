public class Task {
    protected String description; //action to be taken
    protected boolean isDone; //status of the action
    private Ui ui = new Ui();
    protected String line = ui.line();
    protected String StatusIcon;

    public String Output(){
        return "";
    }

    /**
     * This methods updates the Task action and indicate that it is incomplete.
     * @param description This is the action for the Task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.StatusIcon = "\u2718";
    }

    /**
     * This methods retrun the status icon.
     * @return String This return the status icon.
     */
    public void getStatusIcon() {
        if (isDone) {
            this.StatusIcon = "\u2713";
        } else {
            this.StatusIcon = "\u2718";
        }
    }

    public void setStatusIcon() {
        this.StatusIcon =  "1";
    }

    @Override
    public String toString(){
        return "[" + StatusIcon + "] " + description;
    }

}