public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;

    public Task(String description, int index){
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}