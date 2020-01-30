public class Todo extends Task {

    public Todo (String description){
        super(description);
    }

    public Todo (String description, boolean done){
        super(description);
        this.isDone = done;
    }

    public String toString(){
        return ("[T][" + getStatusIcon() + "] " + getDescription());
    }

    public String toParser(){
        return "T /" + getStatusIcon() + "/" + this.description;
    }
}
