public class ByeCommand implements Command {
    public ByeCommand() {
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }
    
    public boolean isExit() {
        return true;
    }
}
