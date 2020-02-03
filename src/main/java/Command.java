public abstract class Command {
    protected String command;
    protected int index;
    protected Task task;

    public String getCommand() {
        return this.command;
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask() {
        return this.task;
    }
}
