public class Task implements TaskPrintable, Parseable {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.type = "T";
        this.description = description;
        this.isDone = false;
    }

    private Task(boolean isDone, String description){
        this.type = "T";
        this.description = description;
        this.isDone = isDone;
    }

    public void setType(String type){
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

    public String toString() {
        return "[" + getType() + "]"
                + " [" + getStatusIcon() + "]  " + getDescription();
    }

    public String print(){
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription();
    }


    public static Task parse(String taskString){

        String[] parts = taskString.split("\\|");
        boolean d = parts[1].trim().equals("1") ? true : false;
        String desc = parts[2];
        return new Task(d, desc);
    }
}