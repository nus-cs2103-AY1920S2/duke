public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    
    protected void setDone() {
        this.isDone = true;
    }
    
    public static Task fromStorage(String data) {
        String[] parsedArgs = data.split(",");
        String type = parsedArgs[0];

        Task output;
        if (type.equals("D")) {
            output = new DeadlineTask(parsedArgs[1], parsedArgs[3]);
        } else if (type.equals("E")) {
            output = new EventTask(parsedArgs[1], parsedArgs[3]);
        } else {
            output = new Task(parsedArgs[1]);
        }

        if (Boolean.parseBoolean(parsedArgs[2])) {
            output.setDone();
        }

        return output;
    }
    
    public String toStorage() {
        return String.format("T,%s,%b", this.description, this.isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}