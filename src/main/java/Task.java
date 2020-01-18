public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void setType(String type){
        if (type.equals("todo")){
            this.type = "T";
        } else if (type.equals("deadline")) {
            this.type = "D";
        } else if (type.equals("event")) {
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