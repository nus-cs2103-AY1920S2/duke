public class Task {

    private String description;
    private boolean isDone;

    public Task(){
    }
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return description;
    }

    public void setDone(){
        this.isDone = true;
    }

    public String getStatusIcon(){
        return ( isDone ? "✓" : "✗" );
    }

    public String toString(){
        return "[T]["+this.getStatusIcon()+"] "+this.getDescription();
    }
}
