import java.lang.StringBuilder;

public class Task {
    boolean isDone;
    String description;

    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void setDone() {
        this.isDone = true;
    }

//    public String getStatusIcon() {
//        return (isDone ? "[done]" : "[undone]" ); //return tick or X symbols
//    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getStatusIcon());
        str.append(" ").append(description);
        return str.toString();
    }
}
