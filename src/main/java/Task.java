public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeD;
    protected String timeD;

    public Task(String description,String typeD) {
        this.description = description;
        this.isDone = false;
        this.typeD = typeD;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void doAct() {
        isDone = true;
    }

    public String getD() {
        return description;
    }

    public void setTime(String time) {
        this.timeD = time;
    }

    public String getType() {
        return this.typeD;
    }

    public String getTime() {
        return this.timeD;
    }
}
