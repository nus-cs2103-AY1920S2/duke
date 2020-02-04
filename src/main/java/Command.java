public abstract class Command {
    protected String command;
    protected int index;
    protected Task task;
    protected String taskToSearch;

    public String getCommand() {
        return this.command;
    }
    public int getIndex() {
        return this.index;
    }
    public String getTaskToSearch() {
        return this.taskToSearch;
    }

    public Task getTask() {
        return this.task;
    }


}
