package task;

public abstract class Task {
    protected boolean check;
    protected String command;

    public Task(String command) {
        this.command = command;
        this.check = false;
    }

    public String getIcon() {
        return (check ? "Y" : "N");
    }

    public void setCheck() {
        this.check = true;
    }

    public abstract String toStringTxt();

    @Override
    public String toString() {
        return "[" + this.getIcon() + "] " + command;
    }
}