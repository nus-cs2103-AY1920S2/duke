package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeD;
    protected String timeD;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
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

    public String getTime() {
        return this.timeD;
    }

    public String getType() {
        return this.typeD;
    }

}
