public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, String status){
        this.description = description;
        this.isDone = status.equals("Yes");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public Task markAsDone(){
        this.isDone = true;
        return this;
    }
    public String checkDone(){
        return(isDone ? "Yes" : "No");
    }
    public String getDescription(){
        return this.description;
    }
    @Override
    public String toString(){
            return " [T][" + this.getStatusIcon() + "]" + this.description;
    }
}
