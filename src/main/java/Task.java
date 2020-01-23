public class Task {
    protected String description;
    protected boolean isDone;
    protected String reply;
    protected static int taskNo = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskNo++;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public boolean getDone(){
        return isDone;
    }
    public String getDescription(){
        return description;
    }
    public String toString(){
        if(isDone == true) {
            reply = "\u2713";
        } else {
            reply = "\u2718";
        }
        return ("[" + reply + "] " + description); //return tick or X symbols
    }
}
