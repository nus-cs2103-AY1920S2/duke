import java.time.LocalDate;

/**
 * Encapsulates an "event" command from the user.
 */
public class EventCommand implements Command {
    private String description;
    private LocalDate eventTime;
    
    /**
     * Constructs a new EventCommand instance.
     * @param description Task description
     * @param eventTime Event time for the task
     */
    public EventCommand(String description, LocalDate eventTime) {
        this.description = description;
        this.eventTime = eventTime;
    }
    
    /**
     * Adds a new Event task specified by this EventCommand into the task list.
     * @param tasks TaskList object to store tasks
     * @param ui UI object for interfacing with the user
     * @param storage Storage object to read and write TaskList state from files
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(description, eventTime);
        tasks.addTask(newTask);
        
        ui.showAddTaskMessage(newTask, tasks.size());
    }
    
    /**
     * Returns false, since "event" is not an exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}

