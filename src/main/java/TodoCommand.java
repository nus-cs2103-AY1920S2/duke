public class TodoCommand implements Command {
    String taskDescription;
    
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(taskDescription);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    public boolean isExit() {
        return false;
    }
}
