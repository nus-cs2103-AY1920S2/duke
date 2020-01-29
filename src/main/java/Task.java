import java.time.LocalDate;

public class Task {
    protected String msg;
    protected String status;
    protected String type;
    protected LocalDate time;

    public Task(String msg) {
        this.msg = msg;
        this.type = "";
        status = "✗";
    }

    public void markDone() {
        status = "✓";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String writeToFile() {
        return type + " , " + status + " ," + msg;
    }

    @Override
    public String toString() {
        return "[" + type + "][" + status + "]" + msg;
    }
}
