package ip;

public abstract class Command {
    boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui);
    public boolean isExit(){
        return isExit;
    }
}
