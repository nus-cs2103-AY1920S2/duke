package dukeClasses;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String s){
        this.description = s;
        this.isDone = false;
    }

    /**
     * returns a string
     * @return returns a string containing the details
     */
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon(){

        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Used to change the isDone boolean of a dukeClasses.Task
     */
    public void markAsDone(){
        this.isDone = true;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }
}
