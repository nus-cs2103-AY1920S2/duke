public class Task {
    protected String description;
    protected boolean isDone;
    protected String line = "____________________________________________________________";
    public void Output(){}

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { return (isDone ? "✓" : "✖"); }
//    public int getStatusIcon() { return (isDone ? 1 : 0); }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}