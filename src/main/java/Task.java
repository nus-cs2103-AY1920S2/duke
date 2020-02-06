public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public String getStatus(){
        return (isDone ? "Tick" : "Untick"); // return tick or X symbol
    }
    public String getDescription(){
        return this.description;
    }
    public void markDone(){
        this.isDone = true;
    }

    public String toString(){
        return "[" + this.getStatus() + "] " + this.getDescription();
    }

    public String getWhen() {
        return "";
    }
    public String extraInfo(){
        return "";
    }
}
