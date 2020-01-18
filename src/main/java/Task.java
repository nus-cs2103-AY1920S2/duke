public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void setType(String type){
        if (type.equalsIgnoreCase(Operation.TODO.toString())){
            this.type = "T";
        } else if (type.equalsIgnoreCase(Operation.DEADLINE.toString())) {
            this.type = "D";
        } else if (type.equalsIgnoreCase(Operation.EVENT.toString())) {
            this.type = "E";
        } else {
            this.type = "";
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription(){
        return description;
    }

    public String getType(){
        return type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString(){
        return "[" + getType() + "]"
                + " [" + getStatusIcon() + "]  " + getDescription();
    }
}