public class FindCommand implements Command {
    String searchString;
    
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMatchingTasksMessage();
        
        int c = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.description().indexOf(searchString) != -1) {
                c++;
                ui.showNumberedEntry(c, task);
            }
        }
    }
    
    public boolean isExit() {
        return false;
    }
}

