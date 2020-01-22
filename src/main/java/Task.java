public class Task {
    protected String msg;
    protected String status;
    protected String type;
    protected String time;

    public Task (String msg) {
        this.msg = msg;
        this.type = "";
        this.time = "";
        status = "[✗]";
    }

    public void markDone() {
        status = "[✓]";
    }

    @Override
    public String toString() {
        return type + status + " " + msg + " " + time;
    }
}
