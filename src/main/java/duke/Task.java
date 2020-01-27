package duke;

import java.lang.StringBuilder;

public abstract class Task {
    boolean isDone;
    String input;

    Task(String input) {
        this.isDone = false;
        this.input = input;
    }

    public void setDone() {
        this.isDone = true;
    }

//    public String getStatusIcon() {
//        return (isDone ? "[done]" : "[undone]" ); //return tick or X symbols
//    }

    // 1 isDone, 0 is not
    public String getIsDone() {
        return (isDone ? "1" : "0" );
    }

    protected String getFormatForSave() {
        StringBuilder str = new StringBuilder(getIsDone());
        str.append(" ").append(input);
        return str.toString();
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getStatusIcon());
        str.append(" ").append(input);
        return str.toString();
    }
}
