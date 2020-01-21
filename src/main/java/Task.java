public class Task {
    protected boolean check;
    protected String command;

    public Task(String command) {
        this.command = command;
        this.check = false;
    }

    public String getIcon() {
        return (check ? "\u2713" : "\u2718");
    }

    public void setCheck() {
        this.check = true;
    }

    @Override
    public String toString() {
        return "[" + this.getIcon() + "] " + command;
    }
}