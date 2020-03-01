package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeD;
    protected String timeD;
    protected String rawDate;
    protected String rawTime;

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

    public String getTimeStamp() { return ""; }

    public String getRawDate() {
        return this.rawDate;
    }

    public String getRawTime() {
        return this.rawTime;
    }
}
