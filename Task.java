public class Task {

    public String description;
    public boolean isDone;


    public Task(String s){
        this.description = s;
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon(){
        return isDone? "\u2713" : "\u2718";
    }

    public void markAsDone(){
        this.isDone = true;
    }
}
