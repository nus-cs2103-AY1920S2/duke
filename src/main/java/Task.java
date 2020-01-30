public class Task {
    protected String description; //action to be taken
    protected boolean isDone; //status of the action
    private Ui ui = new Ui();
    protected String line = ui.line();

    public void Output(){}

    /**
     * This methods updates the Task action and indicate that it is incomplete.
     * @param description This is the action for the Task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * This methods retrun the status icon.
     * @return String This return the status icon.
     */
    public String getStatusIcon() { return (isDone ? "✓" : "✖"); }
//    public int getStatusIcon() { return (isDone ? 1 : 0); }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}