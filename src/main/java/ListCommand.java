public class ListCommand implements Command {
    public ListCommand() {
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.showNumberedEntry(i+1, tasks.getTask(i));
        }
    }
    
    public boolean isExit() {
        return false;
    }
}
