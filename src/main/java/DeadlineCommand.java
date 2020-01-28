import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private String taskDescription;
    private LocalDate deadline;
    
    public DeadlineCommand(String taskDescription, LocalDate deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(taskDescription, deadline);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    public boolean isExit() {
        return false;
    }
}
