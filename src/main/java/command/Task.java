package command;


public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone(){
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✕"); //return tick or X symbols
    }

    @Override
    public String toString(){
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }

    public String fileString(){
        return "D|" + getStatusIcon() + "|" + description;
    }

    public boolean containsString(String keyword){
        return this.toString().contains(keyword);
    }
}
