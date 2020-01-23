import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Task {
    protected String command;
    protected boolean done;

    public Task(String command) {
        this.command = command;
    }

    protected void setDone() {
        done = TRUE;
    }

    protected String getDoneSymbol() {
        return done ? "✓" : "✗" ;
    }

    protected String getCommand() {
        return command;
    }
}
