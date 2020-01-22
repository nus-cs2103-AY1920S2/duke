public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected String line = "____________________________________________________________";
    public void Output(){}

    public Task(String description, int index){
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

//    public String getStatusIcon() {
//        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
//    }
//    public String getStatusIcon() { return (isDone ? Character.toString(10003) : Character.toString(10005));} //return tick or X symbols
    public String getStatusIcon() { return (isDone ? "✓" : "✖"); }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}