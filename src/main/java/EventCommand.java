import java.time.LocalDate;

public class EventCommand implements Command {
    private String taskDescription;
    private LocalDate eventTime;
    
    public EventCommand(String taskDescription, LocalDate eventTime) {
        this.taskDescription = taskDescription;
        this.eventTime = eventTime;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(taskDescription, eventTime);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    public boolean isExit() {
        return false;
    }
}

