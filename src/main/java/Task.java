import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Task {
    protected String command;
    protected int id;
    protected boolean done;

    public Task(String command, int id) {
        this.command = command;
        this.id = id;
    }

    protected void setDone() {
        done = TRUE;
    }

    protected String getDoneSymbol() {
        return done ? "\u2713" : "\u2717";
    }

    protected int getID() {
        return id;
    }

    protected String getCommand() {
        return command;
    }
}
